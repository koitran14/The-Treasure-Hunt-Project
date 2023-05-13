//Purpose: defines an object representing a visual effect that is displayed during dialogue.

package effects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.Dialogue.*;

public class DialogueEffect {

    private int x, y, type;
    private int aniIndex, aniTick;
    private boolean active = true;

    //constructor
    public DialogueEffect(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    //update current animation of effect
    public void update() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(type)) {
                active = false;
                aniIndex = 0;
            }
        }
    }

    // turn off
    public void deactive() {
        active = false;
    }

    // turn on effects and get its intial state with new coordinates
    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        active = true;
    }

    //getter
    public int getAniIndex() {
        return aniIndex;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }
}
