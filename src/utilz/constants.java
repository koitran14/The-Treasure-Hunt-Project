package utilz;

import java.awt.im.InputMethodRequests;

import main.Game;

public class constants {
    public static class EnemyConstants{
        public static final int CRABBY = 0;
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;
        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;
        public static final int CRABBY_WIDTH = (int)(CRABBY_WIDTH_DEFAULT*Game.SCALE);
        public static final int CRABBY_HEGHT = (int)(CRABBY_HEIGHT_DEFAULT*Game.SCALE);

        public static final int CRABBY_DRAWOFFSET_X = (int)(26*Game.SCALE);
        public static final int CRABBY_DRAWOFFSET_Y = (int)(9*Game.SCALE);

        public static int GetSpriteAmount(int enemy_type, int enemy_state){
            switch (enemy_type) {
                case CRABBY:
                    switch(enemy_state){
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;                           
                    }       
            }
            return 0;
        }


        public static int GetMaxHealth(int enemy_type){
            switch (enemy_type) {
                case CRABBY:
                    return 10;
                    
            
                default:
                    return 1;
                    
            }
        }
        public static int getEnemyDmg(int enemy_type){
            switch (enemy_type) {
                case CRABBY:
                    return 15;
            
                default:
                    return 0;
            }
        }
    } 
    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2; //chua ro nhan va
        public static final int FALLING =3;
        public static final int HIT = 4;
        public static final int ATTACK_1 = 5;
        public static final int DEAD = 6;
        


        public static int GetSpriteAmount (int player_action){

            switch(player_action) {
                case DEAD:
                return 8;

                case RUNNING:
                return 8; // so hinh

                case IDLE:
                return 2;

                case HIT:
                return 2;

                case ATTACK_1:
                return 4;

                case FALLING:
                default:
                return 1;
            

            }
        }
    }
}