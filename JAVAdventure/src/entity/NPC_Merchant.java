package entity;

import java.util.Random;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity{
	
	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
		setDialogue();
		setItems();
	}
	public void getImage() {
		up1 = setup("/NPC/merchant_down_1",gp.tileSize,gp.tileSize);
		up2 = setup("/NPC/merchant_down_2",gp.tileSize,gp.tileSize);
		down1 = setup("/NPC/merchant_down_1",gp.tileSize,gp.tileSize);
		down2 = setup("/NPC/merchant_down_2",gp.tileSize,gp.tileSize);
		left1 = setup("/NPC/merchant_down_1",gp.tileSize,gp.tileSize);
		left2 = setup("/NPC/merchant_down_2",gp.tileSize,gp.tileSize);
		right1 = setup("/NPC/merchant_down_1",gp.tileSize,gp.tileSize);
		right2 = setup("/NPC/merchant_down_2",gp.tileSize,gp.tileSize);
	}
	
	public void setDialogue() {
		
		dialogues[0][0] = "How can I help you?";
		dialogues[1][0] = "Come again.";
		dialogues[2][0] = "You need more coins to buy this item.";
		dialogues[3][0] = "Your inventory is full.";
		dialogues[4][0] = "That item is equipped.";
	}
	public void setItems() {
		
		inventory.add(new OBJ_Potion_Red(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Shield_Wood(gp));
		inventory.add(new OBJ_Shield_Blue(gp));
		inventory.add(new OBJ_Tent(gp));
		inventory.add(new OBJ_Lantern(gp));
		
		int i = new Random().nextInt(10) + 1;
		if(i > 5) {
			inventory.add(new OBJ_Coin_Bronze(gp));
		}
	}
	public void speak() {
		
		facePlayer();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}

}
