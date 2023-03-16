package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100; // change from int to float to set the speed of object in movement
    private long lastCheck = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;
    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimation();
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimation() {
        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++)
                animations[i][j] = img.getSubimage(j * 64, i * 40, 64, 40);
    }
    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;

    }
    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setPanelSize() {

        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }
    private void updateAnimationTick(){

        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
            }
        }
    }
    public void setMoving(int direction){
           this.playerDir  = direction;
    }

    private void setAnimation() {
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePos() {
        if (moving){
            switch (playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    public void updateGame(){

        updateAnimationTick();
        setAnimation();
        updatePos();

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // actually, it is calling JComponent's paintComponent; JComponent is the superclass of JPanel

        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta,256,160, null);
    }

}
