package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Obj_Flower extends SuperObject{

	public Obj_Flower(GamePanel gp) {
		this.gp = gp;
		setDefault();
		getObjectImage();
		name = "flower";
	}

	public void setDefault(){
		objectX = (10 * 10 + 4) * gp.tileSize;
		objectY = (7 * 10 + 8) * gp.tileSize;
		screenX = objectX - gp.player.worldX + gp.player.screenX;
		screenY = objectY - gp.player.worldY + gp.player.screenY;
		picked = false;
		consumed = false;
	}


	public void draw(Graphics2D g2) {
		super.draw(g2);
		if(!consumed) {
			int frameCenterX = gp.player.worldX;
			int frameCenterY = gp.player.worldY;

			BufferedImage image = null;
			image = image_obj;
			if(objectX >= frameCenterX - gp.player.screenX 
					&& objectX <= frameCenterX + gp.player.screenX
					&& objectY <= frameCenterY + gp.player.screenY
					&& objectY >= frameCenterY - gp.player.screenY) {
				g2.drawImage(image, screenX, screenY, gp.tileSize/2, gp.tileSize/2, null);
			}
		}
	}

	public void getObjectImage() {
		try {
			image_obj = ImageIO.read(getClass().getResourceAsStream("/objects/flower.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		super.update();
		if(picked && distance(gp.player) >= gp.tileSize / 2) {
			objectX += (gp.player.worldX - objectX)/(gp.player.speed + 2);
			objectY += (gp.player.worldY - objectY)/(gp.player.speed + 2);
		}
		screenX = objectX - gp.player.worldX + ((gp.maxScreenCol/2) * gp.tileSize);
		screenY = objectY - gp.player.worldY + ((gp.maxScreenRow/2) * gp.tileSize);
		checkPick();
	}

	public void checkPick() {
		if(distance(gp.player) <= gp.tileSize) {
			picked = true;
		}
	}
}
