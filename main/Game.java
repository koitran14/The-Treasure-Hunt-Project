package main;

public class Game {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();                //requests this <code>Component</code> get input focus to jpanel
    }
}
