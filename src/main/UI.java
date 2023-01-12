package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Heart;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font purisaB, minecraftia;
	BufferedImage heart_full, heart_blank, heart_half;
	public boolean messageOn = false;
//	public String message = "";
//	int messageCounter = 0;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1: the second screen
	public int slotCol;
	public int slotRow;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/Minecraftia-Regular.ttf");
			minecraftia = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;

	}
	
	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
		
	}
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;

		g2.setFont(minecraftia);
		//g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		
		// CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawPlayerLife();
			drawCharacterScreen();
			drawInventory();
		}
		
	}
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		
		// DRAW MAX LIFE
		while(i < gp.player.maxLife / 2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
	}
	
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * (gp.maxScreenRow - 1);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,16F));
		
		for(int i = 0; i < message.size(); i++) {
			
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);				
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; // messageCounter++
				messageCounter.set(i, counter); // set the counter to the array
				messageY -= 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
			
		}
		
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			g2.setColor(new Color(110,45,25));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,75F));
			String text = "Treasure Hunt";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 4;
			
			// SHADOW
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// PLAYER IMAGE
			x = gp.screenWidth / 2 - (gp.tileSize * 3) / 2;
			y += gp.tileSize - 60;
			g2.drawImage(gp.player.down1,x,y,gp.tileSize*3,gp.tileSize*3,null);
			
			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,38F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize * 5;
			g2.drawString(text,x,y);
			if(commandNum == 0) {
				g2.drawString(">",x - gp.tileSize,y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize + 10;
			g2.drawString(text,x,y);
			if(commandNum == 1) {
				g2.drawString(">",x - gp.tileSize,y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize + 10;
			g2.drawString(text,x,y);
			if(commandNum == 2) {
				g2.drawString(">",x - gp.tileSize,y);
			}
		}
		else if(titleScreenState == 1) {
			
			g2.setColor(new Color(110,45,25));
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			
			// CLASS SELECTION SCREEN
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String text = "Select your class:";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			g2.drawString(text, x, y);
			
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Thief";
			x = getXforCenteredText(text);
			y += gp.tileSize + 10;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize + 10;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}
		
	}
	
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);;
		int y = gp.screenHeight / 2 + gp.tileSize;
		
		g2.drawString(text,x,y);
	}
	
	public void drawDialogueScreen() {
		
		// WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
		x += 25;
		y += gp.tileSize + 5;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line,x,y);
			y += 40;
		}
		
	}
	
	public void drawCharacterScreen() {
		
		// CREATE A FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(15F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 36;
		
		// NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		// VALUE
		int tailX = (frameX + frameWidth) - 30;
		// reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value,tailX);
		g2.drawString(value,textX,textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1,tailX - gp.tileSize,textY - 25,null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1,tailX - gp.tileSize,textY - 25,null);
		
	}
	
	public void drawInventory() {
		
		// FRAME
		int frameX = gp.tileSize * 9;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = gp.tileSize * 5;
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize + 3;
		
		// DRAW PLAYER"S ITEMS
		for(int i =0; i < gp.player.inventory.size(); i++) {
			
			g2.drawImage(gp.player.inventory.get(i).down1,slotX,slotY,null);
			
			slotX += slotSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += slotSize;
			}
		}
		
		
		// CURSOR
		int cursorX = slotXstart + (slotSize * slotCol);
		int cursorY = slotYstart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		
		// DRAW CURSOR
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);
		
		// DESCRIPTION FRAME
		int dFrameX = frameX;
		int dFrameY = frameY + frameHeight;
		int dFrameWidth = frameWidth;
		int dFrameHeight = gp.tileSize * 3;
		drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);
		
		// DRAW DESCRIPTION TEXT
		int textX = dFrameX + 20;
		int textY = dFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(14F));
		
		int itemIndex = getItemIndexOnSlot();
		
		if(itemIndex < gp.player.inventory.size()) {
			
			for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line,textX,textY);
				textY += 32;
			}
		}
	}
	
	public int getItemIndexOnSlot() {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
