package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, fPressed, escPressed, kPressed;
	
	// DEBUG
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		// PLAY STATE
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		// CHARACTER STATE
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		
	}
	
	public void titleState(int code) {
		if(gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_SPACE) {
				if(gp.ui.commandNum == 0) {
					gp.ui.titleScreenState = 1;
				}
				if(gp.ui.commandNum == 1) {
					// add later
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		else if(gp.ui.titleScreenState == 1) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 3;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_SPACE) {
				if(gp.ui.commandNum == 0) {
					System.out.println("FIGHTER");
					gp.gameState = gp.playState;
					gp.player.maxLife = 4;
					gp.player.life = 4;
					gp.player.strength = 4;
					//gp.player.attack = gp.player.strength * gp.player.currentWeapon.attackValue;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1) {
					System.out.println("THIEF");
					gp.gameState = gp.playState;
					gp.player.speed += 3;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 2) {
					System.out.println("SORCERER");
					gp.gameState = gp.playState;
					gp.player.attackArea.width = 72;
					gp.player.attackArea.height = 72;
					gp.player.strength = 2;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 3) {
					gp.ui.titleScreenState = 0;
				}
			}
		}
	}
	
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = true;
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_F) {
			fPressed = true;
		}
		if(code == KeyEvent.VK_K) {
			kPressed = true;
		}
		if(code == KeyEvent.VK_E) {
			gp.gameState = gp.characterState;
		}
		
		// DEBUG
		if(code == KeyEvent.VK_T) {
			if(!checkDrawTime) {
				checkDrawTime = true;	
			}
			else if(checkDrawTime) {
				checkDrawTime = false;
			}
		}
	}
	
	public void pauseState(int code) {
		
		if(!escPressed && code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_F) {
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code) {
		if(code == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W) {
			if(gp.ui.slotRow != 0) {
				gp.ui.slotRow--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.slotCol != 0) {
				gp.ui.slotCol--;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_S) {
			if(gp.ui.slotRow != 3) {
				gp.ui.slotRow++;
				gp.playSE(9);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.slotCol != 4) {
				gp.ui.slotCol++;
				gp.playSE(9);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			escPressed = false;
		}
		if(code == KeyEvent.VK_K) {
			kPressed = false;
		}
		
		
	}

}
