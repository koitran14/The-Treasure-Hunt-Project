package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100; // change from int to float to set the speed of object in movement
    private BufferedImage img, subImg;

    public GamePanel() {

        mouseInputs = new MouseInputs(this);

        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png"); //required to have the slash because of not in the same folder.

        try {
            img = ImageIO.read(is); // choose InputStream input
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension (1280,720); // because in setMinimumSize() required to have Dimension
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    // method to change the position of object
    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // actually, it is calling JComponent's paintComponent; JComponent is the superclass of JPanel

        subImg = img.getSubimage(1*64,8 * 40, 64,40);
        g.drawImage(subImg, (int)xDelta, (int) yDelta,128, 72, null);  //observer: to monitor the status of image before drawing
        // 64 x 40 based on the pixel of one character.
    }
}
