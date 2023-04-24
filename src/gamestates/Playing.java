package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.GameOverOverlay;




public class Playing extends State implements Statemethods {
    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private GameOverOverlay gameOverOverlay;
    private int xLvlOffset;


    private boolean gameOver;


    private void initClasses() {
        player = new Player(200,200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE),this);
        levelManager= new LevelManager(game);
        enemyManager = new EnemyManager(this);
        gameOverOverlay = new  GameOverOverlay(this);
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
    public Player getPlayer() {
        return player;
    }



    @Override
    public void update() {
        levelManager.update();
        player.update();
        enemyManager.update(levelManager.getCurrentLevel().GetLevelData(), player);
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);

        if(gameOver)
            gameOverOverlay.draw(g);
    }


    public void resetAll() {
        //TODO: reset playing, enemy, level
        gameOver = false;
        //pause = false
        //player.resetAll();
        enemyManager.resetAllEnemies();
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox){
        enemyManager.checkEnemyHit(attackBox);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!gameOver)
            if (e.getButton() == MouseEvent.BUTTON1)
            player.setAttacking(true);
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameOver)
            gameOverOverlay.keyPressed(e);
        else
            switch(e.getKeyCode()){
                case KeyEvent.VK_W:
                player.setUp(true);
                case KeyEvent.VK_A:
                player.setLeft(true);
                case KeyEvent.VK_S:
                player.setDown(true);
                case KeyEvent.VK_D:
                player.setRight(true);

                    break;
            }
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!gameOver)
        switch(e.getKeyCode()){
            case KeyEvent.VK_W:
            player.setUp(false);
            case KeyEvent.VK_A:
            player.setLeft(false);
            case KeyEvent.VK_S:
            player.setDown(false);
            case KeyEvent.VK_D:
            player.setRight(false);

                break;
            

        }
        
    }
}



