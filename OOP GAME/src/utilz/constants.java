package utilz;

public class constants {

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
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount (int player_action){

            switch(player_action) {
            
                case RUNNING:
                return 8; // so hinh

                case IDLE:
                return 2;

                case HIT:
                return 2;

                case ATTACK_1:
                return 4;

                case ATTACK_JUMP_1:
                return 8;

                case ATTACK_JUMP_2:
                return 8;
                case GROUND:
                return 3;
                case FALLING:
                default:
                return 1;
            

            }
        }
    }
}
