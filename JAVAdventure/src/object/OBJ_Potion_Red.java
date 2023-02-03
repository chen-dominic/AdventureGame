package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
	
	GamePanel gp;
	
	public static final String objName = "Red Potion";

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_consumable;
		name = objName;
		value = 5;
		down1 = setup("/objects/potion_red",gp.tileSize,gp.tileSize);
		description = "[Red Potion]\nHeals life by " + value;
		price = 25;
		stackable = true;
	}
	public void setDialogue() {
		dialogues[0][0] = "Your life has been recovered by " + value;
	}
	public boolean use(Entity entity) {
		
//		startDialogue(this,0);
		gp.ui.addMessage("Your life has been recovered by " + value);
		entity.life += value;
		gp.playSE(2);
		return true;
	}

}
