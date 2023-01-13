package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage m1I1,m1I2,m1W1,m1W2,m1S1,m1S2,m1A1,m1A2,m1D1,m1D2; //image buffer
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
