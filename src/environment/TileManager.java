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
	public int mapTileNum[][];
	public int mapTileNumDet[][];
	private AffineTransform transform;

	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[70];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
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

	public void loadMap(String s) {
		try {
			InputStream is = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (row < gp.maxWorldRow) {
				String line = br.readLine();
				String numbers[] = line.split(",");
				while (col < gp.maxWorldCol) {
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				col = 0;
				row++;
			}
			br.close();
		}catch(Exception e){

		}
	}

	public void loadMapDet(String s) {
		try {
			InputStream is = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (row < gp.maxWorldRow) {
				String line = br.readLine();
				String numbers[] = line.split(",");
				while (col < gp.maxWorldCol) {
					int num = Integer.parseInt(numbers[col]);
					mapTileNumDet[col][row] = num;
					col++;
				}
				col = 0;
				row++;
			}
			br.close();
		}catch(Exception e){

		}
	}

	public void draw(Graphics2D g2) {
		int offsetX = gp.maxScreenCol/2 + 2;
		int offsetY = gp.maxScreenRow/2 + 2;
		int frameCenterX = gp.player.worldX/gp.tileSize;
		int frameCenterY = gp.player.worldY/gp.tileSize;
		for(int k = 0; k < 3; ++k) {
			switch(k) {
			case 0:
				loadMap("/maps/map_1_base.csv");
				break;
			case 1:
				loadMap("/maps/map_1_body.csv");
				break;
			//case 2:
			//	loadMapDet("/maps/map_1_detail.csv");
			//	break;
			}
			for(int i = frameCenterX - offsetX; i < frameCenterX + offsetX; i ++) {
				for(int j = frameCenterY - offsetY; j < frameCenterY + offsetY; j ++) {

					int x = i * gp.tileSize;
					int y = j * gp.tileSize;
					int screenX = x - gp.player.worldX + gp.player.screenX;
					int screenY = y - gp.player.worldY + gp.player.screenY;
					
					if(k != 2) {
						if(mapTileNum[i][j] != -1) {
							int theta = rotation(mapTileNum[i][j]);
							int n = ((mapTileNum[i][j]) << 3);
							n = (n >> 3);
							BufferedImage tmp = tile[n].image;

							if(theta > 0) {
								tmp = Funcs.rotate(tile[n].image, Math.PI * (double)theta/180, theta);
							}

							g2.drawImage(tmp, screenX, screenY, gp.tileSize, gp.tileSize, null);
						}
					}
					/*else {
						if(mapTileNumDet[i][j] != -1) {
							int theta = direction(mapTileNumDet[i][j]);
							int n = ((mapTileNumDet[i][j]) << 3);
							n = (n >> 3);
							BufferedImage tmp = tile[n].image;

							if(theta > 0) {
								tmp = Funcs.rotate(tile[n].image, Math.PI * (double)theta/180, theta);
							}

							g2.drawImage(tmp, screenX, screenY, gp.tileSize, gp.tileSize, null);
						}
					}*/
				}
			}
		}
	}

	public int rotation(int n) {
		int ret = 0;
		int tmp = ( n >> 29 );
		switch(tmp) {
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
