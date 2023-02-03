package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 30;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		dialogueSet = -1;
		
		getImage();
		setDialogue();
	}
	public void getImage() {
		up1 = setup("/NPC/oldman_up_1",gp.tileSize,gp.tileSize);
		up2 = setup("/NPC/oldman_up_2",gp.tileSize,gp.tileSize);
		down1 = setup("/NPC/oldman_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/NPC/oldman_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/NPC/oldman_left_1",gp.tileSize,gp.tileSize);
		left2 = setup("/NPC/oldman_left_2",gp.tileSize,gp.tileSize);
		right1 = setup("/NPC/oldman_right_1",gp.tileSize,gp.tileSize);
		right2 = setup("/NPC/oldman_right_2",gp.tileSize,gp.tileSize);
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "Hello, lad.";
		dialogues[0][1] = "So you've come to this island to \nfind the treasure?";
		dialogues[0][2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
		dialogues[0][3] = "Well, good luck on you.";
		
		dialogues[1][0] = "If you get tired, drink at the water pool.";
		dialogues[1][1] = "However, the monsters will come back if you drink.";
		dialogues[1][2] = "Don't push yourself too hard.";
		
		dialogues[2][0] = "I wonder how you can open that door...";
	}
	public void setAction() {
		
		if(onPath) {
			
//			int goalCol = 12;
//			int goalRow = 9;
			int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
			
			searchPath(goalCol,goalRow);
		}
		else {
			actionLockCounter++;

			if(actionLockCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1; // pick up a number from 1 to 100

				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75) {
					direction = "right";
				}

				actionLockCounter = 0;
			}
		}
	}
	public void speak() {
		
		// Do this character specific stuff
		
		facePlayer();
		startDialogue(this,dialogueSet);
		
		dialogueSet++;
		
		if(dialogues[dialogueSet][0] == null) {
			
			dialogueSet = 0;
//			dialogueSet--;
		}
		
 
		
//		if(!onPath) {
//			onPath = true;
//		}
//		else if(onPath) {
//			onPath = false;
//			setAction();
//		}
		
	}
	
	
}
