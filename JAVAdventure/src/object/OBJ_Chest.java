package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	
	GamePanel gp;
	
	public static final String objName = "Chest";
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		image = setup("/objects/chest",gp.tileSize,gp.tileSize);
		image2 = setup("/objects/chest_opened",gp.tileSize,gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
	public void setLoot(Entity loot) {
		this.loot = loot;
		
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "You found a " + loot.name + "!" + "\nYour inventory is full.";
		dialogues[1][0] = "You found a " + loot.name + "!\n" + loot.name + " added to your inventory.";
		dialogues[2][0] = "The chest is empty.";
	}
	public void interact() {
		
		if(!opened) {
			gp.playSE(3);
			
			if(!gp.player.canObtainItem(loot)) {
				startDialogue(this,0);
			}
			else {
				startDialogue(this,1);
				down1 = image2;
				opened = true;
			}
		}
		else {
			startDialogue(this,2);
		}
	}

}
