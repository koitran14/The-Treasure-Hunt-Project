// Purpose: to handle the drawing and updating of the game completed overlay, including the image and menu button.
// It also handles user input events such as mouse movements and mouse clicks on the menu button to reset the game
// and return to the main menu.

package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.Constants.*;
import utilz.LoadSave;

public class GameCompletedOverlay {
    private Playing playing;
    private BufferedImage img;
    private MenuButton quit;
    private int imgX, imgY, imgW, imgH;

    public GameCompletedOverlay(Playing playing) {
        this.playing = playing;
        createImg();
        createButtons();
    }

    private void createButtons() {
        quit = new MenuButton(Game.GAME_WIDTH / 2, (int) (270 * Game.SCALE), 2, Gamestate.MENU);
    }

    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.GAME_COMPLETED, FileType.MENU);
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH / 2 - imgW / 2;
        imgY = (int) (100 * Game.SCALE);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img, imgX, imgY, imgW, imgH, null);

        quit.draw(g);
    }

    public void update() {
        quit.update();
    }

    private boolean isIn(MenuButton b, MouseEvent e) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
        quit.setMouseOver(false);

        if (isIn(quit, e))
            quit.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(quit, e)) {
            quit.resetBools();
        }
    }

    public void mousePressed(MouseEvent e){
        if (isIn(quit, e)){
            quit.setMousePressed(true);
            System.exit(0);
        }
        quit.resetBools();
    }
}

