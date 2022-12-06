package main;

import object.*;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Obj_Flower(gp);
		gp.obj[1] = new Obj_Araya(gp);
	}
}
