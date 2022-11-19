package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity tmp, String str) {
		int entityCol;
		int corner_1, corner_2;
		int tileCheck_1, tileCheck_2;
		
		switch (str) {
		case "up":
			corner_1 = tmp.worldX + tmp.solidArea.x;
			corner_2 = corner_1 + tmp.solidArea.width;
			entityCol = (tmp.worldY + tmp.solidArea.y - tmp.speed)/gp.tileSize;
			tileCheck_1 = (gp.tileM.mapTileNum[corner_1/gp.tileSize][entityCol] << 3) >> 3;
			tileCheck_2 = (gp.tileM.mapTileNum[corner_2/gp.tileSize][entityCol] << 3) >> 3;
			if((gp.tileM.tile[tileCheck_1].collision & 0b0010) == 0b0010 || (gp.tileM.tile[tileCheck_2].collision & 0b0010) == 0b0010) {
				tmp.collisionOn = true;
			}
			break;
		case "down": 
			corner_1 = tmp.worldX + tmp.solidArea.x;
			corner_2 = corner_1 + tmp.solidArea.width;
			entityCol = (tmp.worldY + tmp.solidArea.y + tmp.solidArea.height + tmp.speed)/gp.tileSize;
			tileCheck_1 = (gp.tileM.mapTileNum[corner_1/gp.tileSize][entityCol] << 3) >> 3;
			tileCheck_2 = (gp.tileM.mapTileNum[corner_2/gp.tileSize][entityCol] << 3) >> 3;
			if((gp.tileM.tile[tileCheck_1].collision & 0b1000) == 0b1000 || (gp.tileM.tile[tileCheck_2].collision & 0b1000) == 0b1000) {
				tmp.collisionOn = true;
			}
			break;
		case "left": 
			corner_1 = tmp.worldY + tmp.solidArea.y;
			corner_2 = corner_1 + tmp.solidArea.height;
			entityCol = (tmp.worldX + tmp.solidArea.x - tmp.speed)/gp.tileSize;
			tileCheck_1 = (gp.tileM.mapTileNum[entityCol][corner_1/gp.tileSize] << 3) >> 3;
			tileCheck_2 = (gp.tileM.mapTileNum[entityCol][corner_2/gp.tileSize] << 3) >> 3;
			if((gp.tileM.tile[tileCheck_1].collision & 0b0001) == 0b0001 || (gp.tileM.tile[tileCheck_2].collision & 0b0001) == 0b0001) {
				tmp.collisionOn = true;
			}

			break;
		case "right":  
			corner_1 = tmp.worldY + tmp.solidArea.y;
			corner_2 = corner_1 + tmp.solidArea.height;
			entityCol = (tmp.worldX + gp.tileSize - tmp.solidArea.x + tmp.speed)/gp.tileSize;
			tileCheck_1 = (gp.tileM.mapTileNum[entityCol][corner_1/gp.tileSize] << 3) >> 3;
			tileCheck_2 = (gp.tileM.mapTileNum[entityCol][corner_2/gp.tileSize] << 3) >> 3;
			if((gp.tileM.tile[tileCheck_1].collision & 0b0100) == 0b0100 || (gp.tileM.tile[tileCheck_2].collision & 0b100) == 0b0100) {
				tmp.collisionOn = true;
			}
			break;
			 
		case "down_right":
			checkTile(tmp, "right");
			checkTile(tmp, "down");
			break;
		case "up_right":
			checkTile(tmp, "right");
			checkTile(tmp, "up");
			break;
		case "down_left":
			checkTile(tmp, "left");
			checkTile(tmp, "down");
			break;
		case "up_left":
			checkTile(tmp, "up");
			checkTile(tmp, "left");
			break;
		}
	}
	
	public void rotateCol() {
		//TODO
	}
}

