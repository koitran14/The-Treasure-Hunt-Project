package entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


public abstract class entity {
    protected float x;
    protected float y;
    protected int width;
    protected int height;
    protected Rectangle2D.Float hitbox;




    protected entity (float x, float y, int width,int height) {
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;

    }
    protected void drawHitbox (Graphics g){
        // for debugginf the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width ,(int) hitbox.height);
    }


    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float ( x,  y, width, height);
    }
  /*   protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }*/

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }


       
    }
    
