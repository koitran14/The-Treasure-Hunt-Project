package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100; // change from int to float to set the speed of object in movement
    private float xDir = 0.05f, yDir = 0.05f; // like xDelta and yDelta.
    private int frame = 0;
    private long lastCheck = 0;
    private Color color = new Color( 150, 20, 90);
    private Random random;
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
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

        updateRectangle();
        g.setColor(color); //set color for object
        g.fillRect( (int) xDelta, (int) yDelta, 200,50);

        // fps counter, 1 second = 1000 milliseconds
        frame++;
        if (System.currentTimeMillis() - lastCheck >=1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: "+ frame);
            frame = 0;
        }
        repaint();
    }
        public void updateRectangle() {
            xDelta += xDir;
            // change direction
            if (xDelta > 400 || xDelta < 0) {
                xDir *= -1;
                color = getRndColor();
            }
            yDelta += yDir;
            if (yDelta > 400 || yDelta < 0)
                yDir *= -1;
    }

    private Color getRndColor() {
        int r = random.nextInt(255);  //r,b,g is red, blue, green
        int b = random.nextInt(255);
        int g = random.nextInt(255);

        return new Color(r,b,g);
    }
}
