package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    int framenum;

    public Player (GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH= keyH;
         setDefaultValues();
         getPlayerImage();
    }

    public void setDefaultValues(){
        x=100;
        y=100;
        speed=4;
        direction="none";
    }
    public void getPlayerImage(){
        try{
            m1I1 = ImageIO.read(getClass().getResourceAsStream("/player/m1_idle1.png"));
            m1I2 = ImageIO.read(getClass().getResourceAsStream("/player/m1_idle2.png"));
            m1W1 = ImageIO.read(getClass().getResourceAsStream("/player/m1_down1.png"));
            m1W2 = ImageIO.read(getClass().getResourceAsStream("/player/m1_down2.png"));
            m1S1 = ImageIO.read(getClass().getResourceAsStream("/player/m1_up1.png"));
            m1S2 = ImageIO.read(getClass().getResourceAsStream("/player/m1_up2.png"));
            m1A1 = ImageIO.read(getClass().getResourceAsStream("/player/m1_A1.png"));
            m1A2 = ImageIO.read(getClass().getResourceAsStream("/player/m1_A2.png"));
            m1D1 = ImageIO.read(getClass().getResourceAsStream("/player/m1_D1.png"));
            m1D2 = ImageIO.read(getClass().getResourceAsStream("/player/m1_D2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed==true || keyH.downPressed==true
                || keyH.rightPressed==true || keyH.leftPressed==true) {   //for idle (no animation if no key)
            //player movement
            if(keyH.upPressed == true && keyH.rightPressed == true){
                direction = "right";
                y -= speed/java.lang.Math.sqrt(2);
                x += speed/java.lang.Math.sqrt(2);
            }
            else if (keyH.upPressed == true && keyH.leftPressed == true){
                direction = "left";
                y -= speed/java.lang.Math.sqrt(2);
                x -= speed/java.lang.Math.sqrt(2);
            }
            else if (keyH.downPressed == true && keyH.rightPressed == true){
                direction = "right";
                y += speed/java.lang.Math.sqrt(2);
                x += speed/java.lang.Math.sqrt(2);
            }
            else if (keyH.downPressed == true && keyH.leftPressed == true){
                direction = "left";
                y += speed/java.lang.Math.sqrt(2);
                x -= speed/java.lang.Math.sqrt(2);
            }
            else if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            framenum=10;
        }
        else{
            direction="none";
            framenum=15;
        }

        //UPDATE SPRITE COUNT
        spriteCounter++; //update called 60x per second
        if (spriteCounter > framenum) {     //image change every n frames
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    public void draw(Graphics2D g2){
       BufferedImage image = null;

       switch (direction){
           case "up":
               if(spriteNum==1){
                   image = m1W1;
               }
               if(spriteNum==2){
                   image = m1W2;
               }
               break;
           case "down":
               if(spriteNum==1){
                   image = m1S1;
               }
               if(spriteNum==2){
                   image = m1S2;
               }
               break;
           case "left":
               if(spriteNum==1){
                   image = m1A1;
               }
               if(spriteNum==2){
                   image = m1A2;
               }
               break;
           case "right":
               if(spriteNum==1){
                   image = m1D1;
               }
               if(spriteNum==2){
                   image = m1D2;
               }
               break;
           case "none":
               if(spriteNum==1){
                   image = m1I1;
               }
               if(spriteNum==2){
                   image = m1I2;
               }
               break;
       }
       g2.drawImage(image,x,y,gp.tileSize,gp.tileSize, null);
    }
}
