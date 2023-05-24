/*
Member:
1. Tran Ngoc Dang Khoi - ITCSIU21197
2. Nguyen Tran Hoang Ha - ITITIU21127
3. Ha Van Uyen Nhi - ITCSIU21095
4. Nguyen Hoang Quan - ITITIU21291

*Purpose: contain all the methods of gamestate class to handle input events of keyboard.*/

package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().keyReleased(e);
			case PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (Gamestate.state) {
			case MENU -> gamePanel.getGame().getMenu().keyPressed(e);
			case PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);
			case OPTIONS -> gamePanel.getGame().getGameOptions().keyPressed(e);
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// Not In Use
	}
}