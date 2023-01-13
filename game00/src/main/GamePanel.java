package main;

import entity.Player;

import javax.swing.JPanel;
import java.awt.*;

//jpanel for constructor methods
//runnable associated with run function for thread
public class GamePanel extends JPanel implements Runnable{

    //SCREEN SETTINGS
    final int ogTileSize = 24; //16x16 pixels default size of player/ map tiles etc
    final int scale=2; //scale tile to be visible on pc

    public final int tileSize= ogTileSize * scale; //48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler(); //WASD
    Thread gameThread; // can start and stop (establish time)
    Player player = new Player(this,keyH);


    //set default player position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black); //necessary?
        this.setDoubleBuffered(true);// drawings done in offscreen painting buffer
        this. addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this); // passing gampanel to thread constructor
        gameThread.start();
    }

    @Override
    public void run() {
       double drawInterval = 1000000000/FPS;
       double delta = 0;
       long lastTime = System.nanoTime();
       long currentTime;

        while(gameThread != null){  //as long as it exists
            currentTime = System.nanoTime();
            delta+=(currentTime-lastTime)/ drawInterval;
            lastTime =currentTime;
            if(delta>=1) {
                //1 UPDATE: info such as character positions
                update();
                //2 Draw: screen with updated info
                repaint(); // how you call paintComponent
                delta--;
            }

        }

    }
    public void update(){
        //player movement
        player.update();
    }
    public void paintComponent(Graphics g){ //part of java, graphics is 'paintbrush'
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;//convert to subclass of graphics for better control
        player.draw(g2);
        g2.dispose(); //save mem
    }
}
