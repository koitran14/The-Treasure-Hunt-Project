package entities;

import static utilz.constants.PlayerConstants.GetSpriteAmount;
import static utilz.constants.PlayerConstants.IDLE;
import static utilz.constants.PlayerConstants.RUNNING;
import static utilz.constants.PlayerConstants.ATTACK_JUMP_2;
import static utilz.constants.Directions.*;




import java.awt.Graphics;
import utilz.LoadSave;

import java.awt.image.*;

public class Player extends entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 40;
    private int playerAction = utilz.constants.PlayerConstants.IDLE;
    private int playerDir = -1;
    private boolean moving = false, attacking = false;
    private boolean left, up, right,down;
    private float playerSpeed = 2.0f;
    private float xDelta =100   , yDelta =100;


    public Player(float x, float y){
        super(x,y);
        loadAnimations();

    }

        public void update() {

            updatePos();
            updateAnimationTick();
            setAnimation(); 
            
        }

        public void render(Graphics g) {
            g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int)yDelta ,100,100, null);
            System.out.println("Print player");

        }

           private void updateAnimationTick() {
             aniTick++;
              if (aniTick >= aniSpeed) {
               aniTick = 0;
               aniIndex++;
               if(aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
               }
                  
              }  
           }
           private void setAnimation() {
                int startAni = playerAction;
              if(moving)
                   playerAction = RUNNING;
               else
                   playerAction = IDLE;

                if(attacking)
                playerAction = ATTACK_JUMP_2;
                
                if(startAni != playerAction)
                reserAniTick();
           }

           private void reserAniTick() {
             aniTick = 0;
             aniIndex = 0;
           }

   
           private void updatePos() {
            // if (moving) {
            //     switch(playerDir){
            //         case LEFT:
            //         xDelta -= 5;
            //         break;
            //         case UP:
            //         yDelta -=5;
            //         break;
            //         case RIGHT:
            //         xDelta +=5;
            //         break;
            //         case DOWN:
            //         yDelta +=5;
            //         break;

            //     }
            // }    
            
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
           }
        private void loadAnimations(){
          
                BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
                animations = new BufferedImage[9][8];
    
            for (int j = 0; j<9;j++)
               for(int i = 0; i <8;i++)
                  animations[j][i] = img.getSubimage(i*32,j*32,32,32);

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