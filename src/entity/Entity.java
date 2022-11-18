package entity;

import java.awt.*;
import java.awt.image.*;

public class Entity {
	public int worldX, worldY;
	public int speed;
	public double diagSpeed;
	String dir;
	String tmpDir;
	
	public BufferedImage right_1, right_2, right_3, right_4, right_5, right_6;
	public BufferedImage left_1, left_2, left_3, left_4, left_5, left_6;
	public int spriteCounter = 0;
	public int spriteNumber = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn;
}
