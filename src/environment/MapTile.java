package environment;

public class MapTile {
	private int iD;
	private int rotation;
	private int collision;
	
	public MapTile() {
		iD = -1;
		rotation = 0;
		collision = 0;
	}
	
	public int getID() {
		return iD;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public int getCollision() {
		return collision;
	}
	
	public void setID(int n) {
		iD = n;
	}
	
	public void setRotation(int n) {
		rotation = n;
	}
	
	public void setCollision(int n) {
		collision = n;
	}
	
	public void rotateCollision(int rotation) {
		int tmp = collision;
		switch(rotation) {
		case -3:
			tmp = collision >> 1;
			if(tmp << 1 != collision) {
				tmp += 0b1000;
			}
			collision = tmp;
			break;
		case -2:
			rotateCollision(-3);
			rotateCollision(-3);
			break;
		case 3:
			rotateCollision(-3);
			rotateCollision(-3);
			rotateCollision(-3);
			break;
		}
	}
}
