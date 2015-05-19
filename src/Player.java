public class Player {
	float x, y, width, height;
	float xspeed, yspeed;
	float gravity;
	float mana;
	boolean isPunching;
	boolean isBlocking;
	
	public Player(float x, float y) {
		this.x = x;
		this.y = y;
		yspeed = 5;
		xspeed = 0;
		width = 50;
		height = 50;
		gravity = (float)0.8;
		isPunching = false;
		isBlocking = false;
		mana = 100;
	}
	
	
	public Player(float x, float y, float width, float height, float xspeed,
			float yspeed, float mana) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.xspeed = xspeed;
		this.yspeed = yspeed + gravity;
		this.mana = mana;
	}
	
	public boolean intervalOverLaps(float x1, float x2, float x3, float x4) {
		if (x1 < x3 && x2 < x3) {
			return false;
		}
		if (x1 > x4 && x2 > x4) {
			return false;
		}
		return true;
	}

	public boolean isHitting(Player other) {
		if (intervalOverLaps(x, x + width, other.x, other.x + other.width) &&
				intervalOverLaps(y, y + height, other.y, other.y + other.height)) {
			isPunching = true;
			return true;
		}
		return false;
	}
	
	public boolean isBlocking(Player other) {
		if (isHitting(other) == true) {
			isBlocking = true;
			return true;
		}
		return false;
	}
}