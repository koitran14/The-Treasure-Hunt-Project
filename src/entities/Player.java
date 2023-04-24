package entities;

import static utilz.constants.PlayerConstants.GetSpriteAmount;
import static utilz.constants.PlayerConstants.*;
import static utilz.constants.PlayerConstants.RUNNING;
//import static utilz.constants.PlayerConstants.ATTACK_JUMP_2;
import static utilz.constants.Directions.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import utilz.LoadSave;

import java.awt.image.*;

import gamestates.Playing;
import main.Game;

public class Player extends entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 40;
    private int playerAction = utilz.constants.PlayerConstants.IDLE;
    private int playerDir = -1;
    private boolean moving = false, attacking = false;
    private boolean left, up, right,down;
    private float playerSpeed = 2.0f;
    private float xDelta =100   , yDelta =100;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 4 * Game.SCALE;


    //StatusBarUI
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192 * Game.SCALE);
	private int statusBarHeight = (int) (58 * Game.SCALE);
	private int statusBarX = (int) (10 * Game.SCALE);
	private int statusBarY = (int) (10 * Game.SCALE);

	private int healthBarWidth = (int) (150 * Game.SCALE);
	private int healthBarHeight = (int) (4 * Game.SCALE);
	private int healthBarXStart = (int) (34 * Game.SCALE);
	private int healthBarYStart = (int) (14 * Game.SCALE);

    private int maxHealth = 100;
    private int curentHealth = maxHealth;
    private int healthWidth = healthBarHeight;

    //AttackBox
    private Rectangle2D.Float attackBox;

    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked;
    private Playing playing;

    public Player(float x, float y, int width, int heigth, Playing plaing){
        super(x,y, width, heigth);
        this.playing = playing;
        loadAnimations();
        initHitbox(x, y, 20*Game.SCALE, 28*Game.SCALE);
        initAttackBox();
    }
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int)(20*Game.SCALE), (int)(20*Game.SCALE));

    }
        public void update() {
            updateHealthBar(); 
            if(curentHealth <= 0){
                playing.setGameOver(true);
                return;
            }
            
            updateAttackBox();

            updatePos();
            if(attacking)
                checkAttack();
            updateAnimationTick();
            setAnimation();      
        }

        private void checkAttack() {
            if (attackChecked || aniIndex != 1) 
                return;
            attackChecked = true;
            playing.checkEnemyHit(attackBox);
            
        }

        //Attack hitbox
        private void updateAttackBox() {
            if(right){
                attackBox.x = hitbox.x + hitbox.width + (int)(Game.SCALE * 10);
            }else if(left){
                attackBox.x = hitbox.x - hitbox.width - (int)(Game.SCALE * 10);
            }
            attackBox.y = hitbox.y + (Game.SCALE * 10);
        }

        private void updateHealthBar() {
            healthWidth = (int) ((curentHealth/ (float)maxHealth) * healthBarWidth);

        }
        public void render(Graphics g, int lvlOffset) {
            g.drawImage(animations[playerAction][aniIndex],
             (int)(hitbox.x - xDrawOffset) - lvlOffset + flipX, 
             (int)(hitbox.y - yDrawOffset) ,
             width * flipW, heigth, null);

            System.out.println("Print player");
            drawHitbox(g);
            drawAttackbox(g, lvlOffset);
            drawUI(g);

        }

        private void drawAttackbox(Graphics g, int lvlOffsetX) {
            g.setColor(Color.RED);
            g.drawRect((int)attackBox.x - lvlOffsetX, (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
        }


        private void drawUI(Graphics g) {
            g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight,null);
            g.setColor(Color.RED);
            g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
        }



        private void updateAnimationTick() {
             aniTick++;
              if (aniTick >= aniSpeed) {
                aniTick = 0;
                aniIndex++;
                if(aniIndex >= GetSpriteAmount(playerAction)){
                        aniIndex = 0;
                        attacking = false;
                        attackChecked = false;
               }
                  
              }  
           }
           private void setAnimation() {
                int startAni = playerAction;
              if(moving)
                   playerAction = RUNNING;
               else
                   playerAction = IDLE;

                 if(attacking){
                     playerAction = ATTACK_1; //must be attack
                     if(startAni != ATTACK_1){
                        aniIndex = 1;
                        aniTick = 0;
                        return;
                     }
                    
                }
                if(startAni != playerAction)
                reserAniTick();
           }

           private void reserAniTick() {
             aniTick = 0;
             aniIndex = 0;
           }

   
           private void updatePos() {

            moving = false;

            if(left && !right) {
                x -=playerSpeed;
                moving = true;
            }else if (right && !left) {
                x += playerSpeed;
                moving = true;
            }
   
            if (up && !down){
                y -= playerSpeed; 
                moving = true;
            } else if (down && !up) {
                y+= playerSpeed;
                moving = true;
            }

            float xSpeed = 0;

            if(left){
                xSpeed -= playerSpeed;
                flipX = width;
                flipW = -1;
            }
            if (right){
                xSpeed += playerSpeed;
                flipX = 0;
                flipW = 1;
            }
           }

        public void changeHealth(int value){
            curentHealth += value;

            if(curentHealth <=0){
                curentHealth = 0;
                //gameOver();
            }else if(curentHealth >= maxHealth)
                curentHealth = maxHealth;
        }

        private void loadAnimations(){
          
            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
            animations = new BufferedImage[9][8];
    
            for (int j = 0; j<9;j++)
               for(int i = 0; i <8;i++)
                  animations[j][i] = img.getSubimage(i*32,j*32,32,32);

            statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

        }

       public void resetDirBooleans() {
            left = false;
            right = false;
            up = false;
            down = false;
        }
         
         public void setAttacking(boolean attacking) {
            this.attacking = attacking;
         }

        public boolean isLeft() {
            return left;
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public boolean isUp() {
            return up;
        }

        public void setUp(boolean up) {
            this.up = up;
        }

        public boolean isRight() {
            return right;
        }

        public void setRight(boolean right) {
            this.right = right;
        }

        public boolean isDown() {
            return down;
        }

        public void setDown(boolean down) {
            this.down = down;
        }
            
            }