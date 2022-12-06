package hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Dialogue extends Hud{
	
	public Dialogue(GamePanel gp) {
		this.gp = gp;		
	}
	public void draw(Graphics2D g2) {
		super.draw(g2);
		if(visible) {
			int strSize = 36;
			g2.setColor(new Color(0X4B3A26));
			g2.fillRoundRect(5, gp.screenHeight - gp.screenHeight/4, gp.screenWidth - 10, gp.screenHeight/4, 10, 10);
			g2.setColor(new Color(0X997950));
			g2.fillRoundRect(10, gp.screenHeight - gp.screenHeight/4 + 5, gp.screenWidth - 20, gp.screenHeight/4 - 10, 10, 10);
			g2.setColor(Color.black);
			String str_1 = "Thank you Luca!";
			String str_2 = "You brought me back my flower!";

			g2.setFont(new Font("Monospaced", Font.BOLD, 20));
			g2.drawString(str_1, 15, gp.screenHeight - gp.screenHeight/4 + 10 + g2.getFont().getSize());
			g2.drawString(str_2, 15, gp.screenHeight - gp.screenHeight/4 + 10 + 2 * g2.getFont().getSize() + 10);
		}
	}
}
