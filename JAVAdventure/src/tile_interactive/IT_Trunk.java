package tile_interactive;

import java.awt.Rectangle;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile{
	
	GamePanel gp;
	
	public IT_Trunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles_interactive/trunk",gp.tileSize,gp.tileSize);
		
//		solidArea.x = 20;
//		solidArea.y = 38;
//		solidArea.width = 10;
//		solidArea.height = 10;
//		solidArea.x = 22;
//		solidArea.y = 38;
//		solidArea.width = 5;
//		solidArea.height = 5;
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}
