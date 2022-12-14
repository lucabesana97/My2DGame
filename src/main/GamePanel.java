package main;

import java.awt.*;
import java.util.Arrays;  
import javax.swing.JPanel;

import entity.Player;
import environment.TileManager;
import hud.*;
import object.*;

public class GamePanel extends JPanel implements Runnable{

	//Screen settings
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768px
	public final int screenHeight = tileSize * maxScreenRow; //576px

	public final int maxWorldCol = 160;
	public final int maxWorldRow = 120;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow * tileSize;
	final int FPS = 60;
	
	public TileManager tileM = new TileManager(this);
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	public Player player = new Player(this, keyH);
	public CollisionChecker cCheck = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public SuperObject[] obj = new SuperObject[10];
	public Dialogue dial = new Dialogue(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	

	public void setUpGame() {
		aSetter.setObject();
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
			if (timer > 1000000000) {
				timer = 0;
			}
		}
	}
	
	public void update() {
		player.update();
		obj[0].update();
		obj[1].update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		if(player.worldY > obj[0].objectY - tileSize/4) {
			obj[0].draw(g2);
			player.draw(g2);
		} else {
			player.draw(g2);
			obj[0].draw(g2);
		}
		if(player.worldY > obj[1].objectY) {
			obj[1].draw(g2);
			player.draw(g2);
		} else {
			player.draw(g2);
			obj[1].draw(g2);
		}
		dial.draw(g2);
		g2.dispose();
	}


}
