package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
	
	GamePanel gp;
	
	public static final String objName = "Lantern";
	
	public OBJ_Lantern(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_light;
		name = objName;
		lightRadius = 350;
		down1 = setup("/objects/lantern",gp.tileSize,gp.tileSize);
		description = "[Lantern]\nIlluminates surroundings.\nRadius: " + lightRadius;
		price = 200;
		
	}

}
