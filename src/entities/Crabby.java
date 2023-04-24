package entities;
import static utilz.constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Attr;

import static utilz.constants.Directions.*;
import static utilz.HelpMethods.*;
import main.Game;

public class Crabby extends Enemies{
    //Attack box
    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;

    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEGHT, CRABBY);
        initHitbox(x, y,(int) (22*Game.SCALE), (int)(19*Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int)(82 * Game.SCALE), (int)(19*Game.SCALE));
        attackBoxOffsetX= (int)(Game.SCALE * 30);
    }

    public void update(int[][] lvlData,Player player) {
        updateBehavior(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
        
    }
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    // how enemy move
    private void updateBehavior(int[][] lvlData, Player player){
        
        if(firstUpdate)
            firstUpdateCheck(lvlData);
        if(inAir)
            updateInAir(lvlData);
        else{
                switch(enemyState){
                   case IDLE:
                        newState(RUNNING);
                        break;
                   case RUNNING:
                        if(canSeePlayer(lvlData, player))
                            turnTowardsPlayer(player);
                        if(isPlayerCloseForAttack(player))
                            newState(ATTACK);
                        move(lvlData);
                        break;
                    case ATTACK:
                        if(aniIndex == 0)
                            attackChecked = false;
                            
                        if(aniIndex == 3 && !attackChecked)
                            checkEnemyHit(attackBox,player);
                        break;
                    case HIT:
                        break;
                    }        
        }          
    }



    public void draw(Graphics g, int xLvlOffset) {
        g.setColor((Color.RED));
        g.drawRect((int)(attackBox.x - xLvlOffset), (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
    }

    public int flipX(){
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }
    public int flipW(){
        if (walkDir == RIGHT)
            return -1;
        else
            return 1;
    }
    
}
