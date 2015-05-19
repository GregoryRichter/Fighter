public class Specials2 {
	float w, h, xspeed,x,y;
	
	public Specials2 (float a, float b) {
		x = a;
		y= b;
		w = 10;
		h = 10;
		xspeed = 8;
		
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
		if (intervalOverLaps(x, x + w, other.x, other.x + other.width) &&
				intervalOverLaps(y, y + w, other.y, other.y + other.height)) {
			return true;
		}
		return false;
	}
}
