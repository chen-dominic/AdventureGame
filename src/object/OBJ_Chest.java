package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		
		name = "Chest";
		down1 = setup("/objects/chest",gp.tileSize,gp.tileSize);
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
