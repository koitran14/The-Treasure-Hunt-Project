/*
Member:
1. Tran Ngoc Dang Khoi - ITCSIU21197
2. Nguyen Tran Hoang Ha - ITITIU21127
3. Ha Van Uyen Nhi - ITCSIU21095
4. Nguyen Hoang Quan - ITITIU21291

*Purpose: created to be a base class for all game states in this game and contain help method related to.*/

package gamestates;

import java.awt.event.MouseEvent;

import audio.AudioPlayer;
import main.Game;
import ui.MenuButton;

public class State {

	protected Game game;

	public State(Game game) {
		this.game = game;
	}

	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY());
	}

	public Game getGame() {
		return game;
	}

	public void setGamestate(Gamestate state) {
		//change the gamestate
		switch (state) {
			case MENU -> game.getAudioPlayer().playSong(AudioPlayer.MENU_1);
			case PLAYING -> game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
		}
		Gamestate.state = state;
	}
}