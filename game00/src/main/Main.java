package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit button
        window.setResizable(false);//cannot resize window
        window.setTitle("2D Adventure");

        GamePanel gamePanel= new GamePanel();
        window.add(gamePanel);

        window.pack();// window will fit preferred size of subcomponent gamePanel

        window.setLocationRelativeTo(null);//location not specified default center of screen
        window.setVisible(true); //so we can see window

        gamePanel.startGameThread();
    }

}
