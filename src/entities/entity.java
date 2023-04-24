package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class entity {
    protected float x;
    protected float y;
    protected int width,heigth;
    protected Rectangle2D.Float hitbox;
    
    public entity(float x, float y, int width, int heigth) {
        this.x=x;
        this.y=y;
        this.width = width;
        this.heigth = heigth;

    }
    protected void drawHitbox(Graphics g){
        //debugging hit box
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float( x,  y, width, heigth);
    }

    public void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }
    
}