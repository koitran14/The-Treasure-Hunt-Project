//Purpose: defines an object representing a visual effect
// of raindrops falling down the screen.

package effects;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.Game;
import utilz.Constants;
import utilz.LoadSave;

public class Rain {

    private Point2D.Float[] drops;
    private Random rand;
    private float rainSpeed = 1.3f;
    private BufferedImage rainParticle;

    // Worth knowing, adding particles this way can cost a lot in
    // computer power. Disable it if the game lags.
    public Rain() {
        rand = new Random();
        drops = new Point2D.Float[1000]; // -> store the positions of raindrops
        rainParticle = LoadSave.GetSpriteAtlas(LoadSave.RAIN_PARTICLE, Constants.FileType.OBJECT);
        initDrops();
    }

    private void initDrops() {
        for (int i = 0; i < drops.length; i++)
            drops[i] = getRndPos();
    }

    // returns a new Point2D.Float object with a random x position
    // and a y position set to a random integer between 0 and the game window height
    private Point2D.Float getRndPos() {
        return new Point2D.Float((int) getNewX(0), rand.nextInt(Game.GAME_HEIGHT));
    }


    // updates the positions of all the raindrops by moving them down the screen
    // at a fixed speed rainSpeed. If a raindrop reaches the bottom of the screen,
    // its position is reset to the top with a new x position determined by getNewX(xLvlOffset).
    public void update(int xLvlOffset) {
        for (Point2D.Float p : drops) {
            p.y += rainSpeed;
            if (p.y >= Game.GAME_HEIGHT) {
                p.y = -20;
                p.x = getNewX(xLvlOffset);
            }
        }
    }

    // return a new x position for a raindrop
    private float getNewX(int xLvlOffset) {
        float value = (-Game.GAME_WIDTH) + rand.nextInt((int) (Game.GAME_WIDTH * 3f)) + xLvlOffset;
        return value;
    }

    public void draw(Graphics g, int xLvlOffset) {
        for (Point2D.Float p : drops)
            g.drawImage(rainParticle, (int) p.getX() - xLvlOffset, (int) p.getY(), 3, 12, null);
    }

}
