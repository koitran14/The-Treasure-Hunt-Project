package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){

        jframe = new JFrame();

        jframe.setSize(400,400);                    //set size của cửa sổ
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //set up đóng hoàn toàn
        jframe.add(gamePanel);                                   //add cái đã draw ở gamePanel qua.
        jframe.setLocationRelativeTo(null);                     //set screen at the center
        jframe.setResizable(false);                             //unallow to resize the window
        jframe.pack();                                          //fit windows
        jframe.setVisible(true);                                //set hiện khung cửa sổ
    }
}
