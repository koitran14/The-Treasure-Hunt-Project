/*
Member:
1. Tran Ngoc Dang Khoi - ITCSIU21197
2. Nguyen Tran Hoang Ha - ITITIU21127
3. Ha Van Uyen Nhi - ITCSIU21095
4. Nguyen Hoang Quan - ITITIU21291

*Purpose:  to create Spike object. */

package objects;

import main.Game;

public class Spike extends GameObject{

    public Spike(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32, 16);
        xDrawOffset = 0;
        yDrawOffset = (int)(Game.SCALE * 16);
        hitbox.y += yDrawOffset;
    }
}
