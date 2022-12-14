package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Araya extends SuperObject{

	public Obj_Araya(GamePanel gp){
		this.gp = gp;
		setDefault();
		getObjectImage();
		name = "araya";
	}

	public void setDefault(){
		objectX = (7 * 10 + 7) * gp.tileSize + gp.tileSize / 2;
		objectY = (6 * 10 + 4) * gp.tileSize + gp.tileSize / 2;
		screenX = objectX - gp.player.worldX + gp.player.screenX;
		screenY = objectY - gp.player.worldY + gp.player.screenY;
		picked = false;
		consumed = false;
		size = gp.tileSize * 7 / 8;
	}

	public void getObjectImage() {
		try {
			image_obj = ImageIO.read(getClass().getResourceAsStream("/objects/araya.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2) {
		super.draw(g2);
		int frameCenterX = gp.player.worldX;
		int frameCenterY = gp.player.worldY;

		BufferedImage image = null;
		image = image_obj;
		
		if(objectX >= frameCenterX - gp.player.screenX - 2 * gp.tileSize
				&& objectX <= frameCenterX + gp.player.screenX + 2 * gp.tileSize
				&& objectY <= frameCenterY + gp.player.screenY + 2 * gp.tileSize
				&& objectY >= frameCenterY - gp.player.screenY - 2 * gp.tileSize) {
			g2.drawImage(image, screenX - gp.tileSize / 2, screenY - gp.tileSize / 2, size, size, null);
		}
	}
	
	public void update() {
		super.update();
		screenX = objectX - gp.player.worldX + ((gp.maxScreenCol/2) * gp.tileSize);
		screenY = objectY - gp.player.worldY + ((gp.maxScreenRow/2) * gp.tileSize);
	}
}
