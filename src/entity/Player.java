package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = (gp.screenWidth - gp.tileSize)/2;
		screenY = (gp.screenHeight - gp.tileSize)/2;

		setDefault();
		getPlayerImage();

		solidArea = new Rectangle(20, 22, 10, 20);
	}

	public void setDefault(){
		worldX = 12 * gp.tileSize *10 + 5;
		worldY = 7 * gp.tileSize *10 + 5;
		speed = 6;
		dir = "down";
		diagSpeed = speed * Math.sqrt(2)/2;
	}

	public void update() {

		if(keyH.getUp() == true && keyH.getLeft() == true) {
			dir = "up_left";
		}
		else if(keyH.getUp() == true && keyH.getRight() == true) {
			dir = "up_right";
		}
		else if(keyH.getUp() == true) {
			dir = "up";
		}
		else if(keyH.getDown() == true && keyH.getLeft() == true) {
			dir = "down_left";
		}
		else if(keyH.getDown() == true && keyH.getRight() == true) {
			dir = "down_right";
		}
		else if(keyH.getDown() == true) {
			dir = "down";
		}
		else if(keyH.getLeft() == true) {
			dir = "left";
		}
		else if(keyH.getRight() == true) {
			dir = "right";
		}
		else if(!keyH.getDown() && !keyH.getLeft() && !keyH.getRight() && !keyH.getUp()) {
			if(tmpDir == "left")
				dir = "still_l";
			else 
				dir = "still_r";
		}

		collisionOn = false;
		gp.cCheck.checkTile(this, dir);
		
		if(collisionOn == false) {
			switch (dir) {
			case "up": 
				worldY -= speed;
				break;
			case "up_left": 
				worldY -= diagSpeed;
				worldX -= diagSpeed;
				break;
			case "up_right": 
				worldY -= diagSpeed;
				worldX += diagSpeed;
				break;
			case "down": 
				worldY += speed;
				break;
			case "down_left": 
				worldY += diagSpeed;
				worldX -= diagSpeed;
				break;
			case "down_right": 
				worldY += diagSpeed;
				worldX += diagSpeed;
				break;
			case "left": 
				worldX -= speed;
				break;
			case "right": 
				worldX += speed;
				break;
			}
		}
		
		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNumber < 6) {
				spriteNumber++;
			}
			else if (spriteNumber == 6) {
				spriteNumber = 1;
			}
			spriteCounter = 0;
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (dir){
		case "up":
			switch(spriteNumber) {
			case 1:
				image = right_1;
				break;
			case 2:
				image = right_2;
				break;
			case 3:
				image = right_3;
				break;
			case 4:
				image = right_4;
				break;
			case 5:
				image = right_5;
				break;
			case 6:
				image = right_6;
				break;
			}
			break;
		case "up_right":
			switch(spriteNumber) {
			case 1:
				image = right_1;
				break;
			case 2:
				image = right_2;
				break;
			case 3:
				image = right_3;
				break;
			case 4:
				image = right_4;
				break;
			case 5:
				image = right_5;
				break;
			case 6:
				image = right_6;
				break;
			}
			break;
		case "up_left":
			switch(spriteNumber) {
			case 1:
				image = left_1;
				break;
			case 2:
				image = left_2;
				break;
			case 3:
				image = left_3;
				break;
			case 4:
				image = left_4;
				break;
			case 5:
				image = left_5;
				break;
			case 6:
				image = left_6;
				break;
			}
			break;
		case "down":
			switch(spriteNumber) {
			case 1:
				image = left_1;
				break;
			case 2:
				image = left_2;
				break;
			case 3:
				image = left_3;
				break;
			case 4:
				image = left_4;
				break;
			case 5:
				image = left_5;
				break;
			case 6:
				image = left_6;
				break;
			}
			break;
		case "down_left":
			switch(spriteNumber) {
			case 1:
				image = left_1;
				break;
			case 2:
				image = left_2;
				break;
			case 3:
				image = left_3;
				break;
			case 4:
				image = left_4;
				break;
			case 5:
				image = left_5;
				break;
			case 6:
				image = left_6;
				break;
			}
			break;
		case "down_right":
			switch(spriteNumber) {
			case 1:
				image = right_1;
				break;
			case 2:
				image = right_2;
				break;
			case 3:
				image = right_3;
				break;
			case 4:
				image = right_4;
				break;
			case 5:
				image = right_5;
				break;
			case 6:
				image = right_6;
				break;
			}
			break;
		case "left":
			switch(spriteNumber) {
			case 1:
				image = left_1;
				break;
			case 2:
				image = left_2;
				break;
			case 3:
				image = left_3;
				break;
			case 4:
				image = left_4;
				break;
			case 5:
				image = left_5;
				break;
			case 6:
				image = left_6;
				break;
			}
			tmpDir = "left";
			break;
		case "right":
			switch(spriteNumber) {
			case 1:
				image = right_1;
				break;
			case 2:
				image = right_2;
				break;
			case 3:
				image = right_3;
				break;
			case 4:
				image = right_4;
				break;
			case 5:
				image = right_5;
				break;
			case 6:
				image = right_6;
				break;
			}
			tmpDir = "right";
			break;
		case "still_l":
			image = left_3;
			break;
		case "still_r":
			image = right_3;
			break;
		}

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

	public void getPlayerImage() {
		try {
			right_1 = ImageIO.read(getClass().getResourceAsStream("/player/right_char_1.png"));
			right_2 = ImageIO.read(getClass().getResourceAsStream("/player/right_char_2.png"));
			right_3 = ImageIO.read(getClass().getResourceAsStream("/player/right_char_3.png"));
			right_4 = ImageIO.read(getClass().getResourceAsStream("/player/right_char_4.png"));
			right_5 = ImageIO.read(getClass().getResourceAsStream("/player/right_char_5.png"));
			right_6 = ImageIO.read(getClass().getResourceAsStream("/player/right_char_6.png"));
			left_1 = ImageIO.read(getClass().getResourceAsStream("/player/left_char_1.png"));
			left_2 = ImageIO.read(getClass().getResourceAsStream("/player/left_char_2.png"));
			left_3 = ImageIO.read(getClass().getResourceAsStream("/player/left_char_3.png"));
			left_4 = ImageIO.read(getClass().getResourceAsStream("/player/left_char_4.png"));
			left_5 = ImageIO.read(getClass().getResourceAsStream("/player/left_char_5.png"));
			left_6 = ImageIO.read(getClass().getResourceAsStream("/player/left_char_6.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
