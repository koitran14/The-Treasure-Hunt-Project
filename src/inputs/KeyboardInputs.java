package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;


    public class KeyboardInputs implements KeyListener
    {
        private GamePanel gamePanel;
        public KeyboardInputs(GamePanel gamePanel){
            this.gamePanel = gamePanel;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (Gamestate.state) {
                case MENU:
                    gamePanel.getGame().getMenu().keyPressed(e);
                    break;
                case PLAYING:
                    gamePanel.getGame().getMenu().keyPressed(e);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyReleased (KeyEvent e) {
            switch (Gamestate.state) {
                case MENU:
                    gamePanel.getGame().getMenu().keyReleased(e);
                    break;
                case PLAYING:
                    gamePanel.getGame().getMenu().keyReleased(e);
                    break;
                default:
                    break;
            }
           

        }
    }
