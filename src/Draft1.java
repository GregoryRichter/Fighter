import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;


public class Draft1 extends PApplet {
	int combo;
	float playerX, playerY, player2X, player2Y;
	float playerYSpeed;
	float playerW, playerH;
	float player2YSpeed;
	float player2W, player2H;
	float gravity;
	PFont myFont;
	PImage img;
	int color1, color2;
	
	public void setup() {
		size(600, 451);
		playerX = 100;
		playerY = 510;
		player2X = 500;
		player2Y = 510;
		gravity = (float) 0.8;
		playerW = 50;
		playerH = 50;
		player2W = 50;
		player2H = 50;
		combo = 0;
		myFont = createFont("Georgia", 32);
		textFont(myFont);
		img = loadImage("D:\\Space.png");
		color1 = color(0,250,35);
		color2 = color(255, 45, 0);
	}
	
	public void draw() {
		background(img);
		playerYSpeed = playerYSpeed + gravity;
		playerY = playerY + playerYSpeed;
		player2YSpeed = player2YSpeed + gravity;
		player2Y = player2YSpeed + player2Y;
		
		
		if (playerY >= 510) {
			playerYSpeed = 0;
			playerY = 510;
		}
		
		if (player2Y >= 510) {
			player2YSpeed = 0;
			player2Y = 510;
		}
		
		if (player2X < playerX + playerW && player2X > playerX) {
			if (player2Y > playerY && player2Y < playerY + playerH) {
				fill(color(255,0,0));
			} else {
				combo = combo + 1;
			}   
		}
		
		

		
		rect(playerX, playerY, playerW, playerH);
		fill(255);
		rect(player2X,player2Y,player2W,player2H);
		stroke(0);
		line(0,560,600,560);
		
		if (combo < 150){
			fill(color2);
			text("Combo: " + combo, 300, 50);
		} else {
			fill(color1);
			text("You Win!", 250, 50);
		}
	}
	
	public void keyReleased() {
		if (key == ' ') {
			playerYSpeed = -10;
		}
		if (key == 'w') {
			player2YSpeed = -10;
		}
	}
	public void keyPressed() {
		if (key == CODED) {
			if (keyCode == LEFT) {
				player2X = player2X - 5;
			} else if (keyCode == RIGHT) {
				player2X = player2X + 5;
			}
			if(key == 'd' && keyCode == LEFT){
				playerX = playerX + 5;
				player2X = player2X - 5; 
			}
			if(key == 'a' && keyCode == RIGHT){
				playerX = playerX - 5;
				player2X = player2X + 5; 
			}
		}
		if (key == 'a') playerX = playerX - 5;
		if (key == 'd') playerX = playerX + 5; 
	}
	
}
