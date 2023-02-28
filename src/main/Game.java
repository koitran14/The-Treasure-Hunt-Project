package main;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;      // thread
    private final int FPS_SET = 120;
    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();                //requests this <code>Component</code> get input focus to jpanel
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        int frame = 0;

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame){

                gamePanel.repaint();
                lastFrame = now;
                frame++;
            }

            if (System.currentTimeMillis() - lastCheck >=1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+  frame);
                frame = 0;
            }
        }
    }
}
