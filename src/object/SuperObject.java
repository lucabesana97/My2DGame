package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import entity.*;
import main.GamePanel;
import main.KeyHandler;

public class SuperObject {
	public int objectX, objectY;
	public int screenX, screenY;
	public String name;
	boolean picked, consumed;
	public BufferedImage image_obj;
	GamePanel gp;
	public int size;
	
	public void update() {}
	public void draw(Graphics2D g2) {}
	
	public double distance(Entity tmp) {
		double dist = Math.sqrt((tmp.worldX - objectX) * (tmp.worldX - objectX) + (tmp.worldY - objectY) * (tmp.worldY - objectY));
		return dist;
	}
}
