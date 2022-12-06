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
			tileCheck_1 = gp.tileM.mapTileNum[corner_1/gp.tileSize][entityCol].getCollision();
			tileCheck_2 = gp.tileM.mapTileNum[corner_2/gp.tileSize][entityCol].getCollision();
			if((tileCheck_1 & 0b0010) == 0b0010 || (tileCheck_2 & 0b0010) == 0b0010) {
				tmp.collision_up = true;
			}
			break;
		case "down": 
			corner_1 = tmp.worldX + tmp.solidArea.x;
			corner_2 = corner_1 + tmp.solidArea.width;
			entityCol = (tmp.worldY + tmp.solidArea.y + tmp.solidArea.height + tmp.speed)/gp.tileSize;
			tileCheck_1 = gp.tileM.mapTileNum[corner_1/gp.tileSize][entityCol].getCollision();
			tileCheck_2 = gp.tileM.mapTileNum[corner_2/gp.tileSize][entityCol].getCollision();
			if((tileCheck_1 & 0b1000) == 0b1000 || (tileCheck_2 & 0b1000) == 0b1000) {
				tmp.collision_down = true;
			}
			break;
		case "left": 
			corner_1 = tmp.worldY + tmp.solidArea.y;
			corner_2 = corner_1 + tmp.solidArea.height;
			entityCol = (tmp.worldX + tmp.solidArea.x - tmp.speed)/gp.tileSize;
			tileCheck_1 = gp.tileM.mapTileNum[entityCol][corner_1/gp.tileSize].getCollision();
			tileCheck_2 = gp.tileM.mapTileNum[entityCol][corner_2/gp.tileSize].getCollision();
			if((tileCheck_1 & 0b0001) == 0b0001 || (tileCheck_2 & 0b0001) == 0b0001) {
				tmp.collision_left = true;
			}

			break;
		case "right":  
			corner_1 = tmp.worldY + tmp.solidArea.y;
			corner_2 = corner_1 + tmp.solidArea.height;
			entityCol = (tmp.worldX + gp.tileSize - tmp.solidArea.x + tmp.speed)/gp.tileSize;
			tileCheck_1 = gp.tileM.mapTileNum[entityCol][corner_1/gp.tileSize].getCollision();
			tileCheck_2 = gp.tileM.mapTileNum[entityCol][corner_2/gp.tileSize].getCollision();
			if((tileCheck_1 & 0b0100) == 0b0100 || (tileCheck_2 & 0b100) == 0b0100) {
				tmp.collision_right = true;
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

	/*public void checkObj(Entity tmp, String str) {
		int entityCol;
		int corner_1, corner_2, side;
		int objCorner_1, objCorner_2, objSide;

		switch (str) {
		case "up":
			corner_1 = tmp.worldX + tmp.solidArea.x;
			corner_2 = corner_1 + tmp.solidArea.width;
			side = tmp.worldY + tmp.solidArea.y;
			objCorner_1 = gp.obj[1].objectX;
			objCorner_2 = gp.obj[1].objectX + gp.obj[1].size;
			objSide = gp.obj[1].objectY + gp.obj[1].size;
			if(objCorner_1 >= corner_1 && objCorner_1 <= corner_2) {
				if(side + tmp.speed <= objSide && side >= objSide){
					tmp.collision_up = true;
				}
			}
			break;
		case "down": 
			corner_1 = tmp.worldX + tmp.solidArea.x;
			corner_2 = corner_1 + tmp.solidArea.width;
			side = tmp.worldY + tmp.solidArea.y + tmp.solidArea.height;
			objCorner_1 = gp.obj[1].objectX;
			objCorner_2 = gp.obj[1].objectX + gp.obj[1].size;
			objSide = gp.obj[1].objectY;
			if(objCorner_1 >= corner_1 && objCorner_1 <= corner_2) {
				if(side + tmp.speed >= objSide && side <= objSide){
					tmp.collision_up = true;
				}
			}
			break;
		case "left": 
			corner_1 = tmp.worldY + tmp.solidArea.y;
			corner_2 = corner_1 + tmp.solidArea.height;
			side = tmp.worldX + tmp.solidArea.x;
			objCorner_1 = gp.obj[1].objectY;
			objCorner_2 = gp.obj[1].objectY + gp.obj[1].size;
			objSide = gp.obj[1].objectX + gp.obj[1].size;
			if(objCorner_1 >= corner_1 && objCorner_1 <= corner_2) {
				if(side + tmp.speed <= objSide && side >= objSide){
					tmp.collision_up = true;
				}
			}
			break;
		case "right":  
			corner_1 = tmp.worldY + tmp.solidArea.y;
			corner_2 = corner_1 + tmp.solidArea.height;
			side = tmp.worldX + tmp.solidArea.x + tmp.solidArea.width;
			objCorner_1 = gp.obj[1].objectY;
			objCorner_2 = gp.obj[1].objectY + gp.obj[1].size;
			objSide = gp.obj[1].objectX;
			if(objCorner_1 >= corner_1 && objCorner_1 <= corner_2) {
				if(side + tmp.speed >= objSide && side <= objSide){
					tmp.collision_up = true;
				}
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
	}*/
}

