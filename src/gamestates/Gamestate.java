/*
Member:
1. Tran Ngoc Dang Khoi - ITCSIU21197
2. Nguyen Tran Hoang Ha - ITITIU21127
3. Ha Van Uyen Nhi - ITCSIU21095
4. Nguyen Hoang Quan - ITITIU21291

*Purpose: used to store and access and the current state of the game */

package gamestates;

public enum Gamestate {
	PLAYING, MENU, OPTIONS, QUIT;

	public static Gamestate state = MENU;
}
