package environment;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import calc.Funcs;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public MapTile mapTileNum[][];
	public int mapTileNumDet[][];
	private AffineTransform transform;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[70];
		getTileImage();
		mapTileNum = new MapTile[gp.maxWorldCol][gp.maxWorldRow];
		transform = new AffineTransform();
	}

	public void getTileImage(){
		try {
			for(int i = 0; i <= 69; i++) {
				String str = "/plains/plains_" + Integer.toString(i) + ".png";
				tile[i] = new Tile();
				tile[i].image = ImageIO.read(getClass().getResourceAsStream(str));
			}
			tile[58].collision = 0b0101;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String s, MapTile map[][]) {
		int row = 0;
		int col = 0;
		try {
			InputStream is = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			for(int i = 0; i < gp.maxWorldCol; i++) {
				for(int j = 0; j < gp.maxWorldRow; j++) {
					map[i][j] = new MapTile();
				}
			}

			for(row = 0; row < gp.maxWorldRow; row++) {
				String line = br.readLine();
				String numbers[] = line.split(",");
				for(col = 0; col < gp.maxWorldCol; col++) {
					int num = Integer.parseInt(numbers[col]);
					if(num != -1) {
						map[col][row].setID((num << 3) >> 3);
						map[col][row].setRotation(num >> 29);
						mapTileNum[col][row].setCollision(tile[map[col][row].getID()].collision);
						if(mapTileNum[col][row].getRotation() != 0) {
							mapTileNum[col][row].rotateCollision(mapTileNum[col][row].getRotation());
						}
					}
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println("You dumb fuck, don't make body maps with -1 in them:" + "\t" + col + "\t" + row + "\t" + s);
		}
	}

	public void draw(Graphics2D g2) {
		for(int k = 0; k < 3; ++k) {
			switch(k) {
			case 0:
				loadMap("/maps/map_1_base.csv", mapTileNum);
				drawMap(g2, mapTileNum);
				break;
			case 1:
				loadMap("/maps/map_1_body.csv", mapTileNum);
				drawMap(g2, mapTileNum);
				break;
			case 2:
				MapTile tmp[][] = new MapTile[gp.maxWorldCol][gp.maxWorldRow];
				loadMap("/maps/map_1_detail.csv", tmp);
				drawMap(g2, tmp);
				break;
			}
		}
	}

	
	public void drawMap(Graphics2D g2, MapTile[][] map) {
		
		int offsetX = gp.maxScreenCol/2 + 2;
		int offsetY = gp.maxScreenRow/2 + 2;
		int frameCenterX = gp.player.worldX/gp.tileSize;
		int frameCenterY = gp.player.worldY/gp.tileSize;
		
		for(int i = frameCenterX - offsetX; i < frameCenterX + offsetX; i ++) {
			for(int j = frameCenterY - offsetY; j < frameCenterY + offsetY; j ++) {

				int x = i * gp.tileSize;
				int y = j * gp.tileSize;
				int screenX = x - gp.player.worldX + gp.player.screenX;
				int screenY = y - gp.player.worldY + gp.player.screenY;

				if(map[i][j].getID() != -1) {
					int theta = rotation(map[i][j].getRotation());
					BufferedImage tmp = tile[map[i][j].getID()].image;

					if(theta > 0) {
						tmp = Funcs.rotate(tmp, Math.PI * (double)theta/180, theta);
					}

					g2.drawImage(tmp, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
	
	public int rotation(int n) {
		int ret = 0;
		switch(n) {
		case 0:
			ret = 0;
			break;
		case -3:
			ret = 270;
			break;
		case -2:
			ret = 180;
			break;
		case 3:
			ret = 90;
			break;
		}
		return ret;
	}
}
