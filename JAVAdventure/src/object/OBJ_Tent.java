package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{

	GamePanel gp;
	
	public static final String objName = "Tent";
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		down1 = setup("/objects/tent",gp.tileSize,gp.tileSize);
		description = "[Tent]\nSleep through the night.\nHP & MP Regenerates";
		price = 300;
		stackable = true;
		
		setDialogue();
	}
	public void setDialogue() {
		dialogues[0][0] = "You can only sleep at night.";
	}
	public boolean use(Entity entity) {
		if(gp.eManager.lighting.dayState == gp.eManager.lighting.day && gp.eManager.lighting.filterAlpha < 0.3f) {
			startDialogue(this,0);
			return false;
		}
		else {
			gp.gameState = gp.sleepState;
			gp.playSE(14);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.player.getSleepingImage(down1);
			return true;
		}

	}

}
