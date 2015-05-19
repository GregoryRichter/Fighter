import java.util.ArrayList;
import java.applet.Applet;
import java.awt.Menu;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class GameWithObjects extends PApplet {
	int hitColor;
	Player player, player2;
	Health health1;
	Health2 health2;
	ArrayList<Specials> specialList;
	ArrayList<Specials2> specialList2;
	float gravity;
	PFont myFont;
	PImage img, image1, image2, image1Punch, p1CurrentImage, p2CurrentImage,
			p1Hadoken, p2Hadoken, image2Punch, image1Block, image2Block,
			image1Kick, image2Kick, filler;
	boolean GameStart;
	int counter;

	public void setup() {
		MP3 mp3 = new MP3("../Mortal.mp3");
		mp3.play();
		size(900, 900);
		player = new Player(120, 650);
		player2 = new Player(480, 650);
		gravity = (float) 0.8;
		health1 = new Health(0, 0);
		health2 = new Health2(500, 0);
		myFont = createFont("Georgia", 24);
		textFont(myFont);
		img = loadImage("../Armory.png");
		image1 = loadImage("../catgif.gif");
		image2 = loadImage("../face.png");
		image1Punch = loadImage("../nyan.png");
		image2Punch = loadImage("../kitty.jpg");
		image1Block = loadImage("../8.jpg");
		image2Block = loadImage("../lotus.jpg");
		image1Kick = loadImage("../kick.jpg");
		image2Kick = loadImage("../kick2.jpg");
		p1Hadoken = loadImage("../mush.jpg");
		p2Hadoken = loadImage("../mushr.png");
		filler = loadImage("../jon.jpg");
		specialList = new ArrayList<Specials>();
		specialList2 = new ArrayList<Specials2>();
		hitColor = color(255, 0, 0);
		p1CurrentImage = image1;
		p2CurrentImage = image2;
		GameStart = false;
		counter = 0;
		
	}

	public void draw() {
		rect(300, 300, 300, 300);
		fill(0, 255, 0);
		rect(300, 300, 30, 30);
		if (mousePressed) {
			GameStart = true;
		}
		if (GameStart) {
			counter++;
			if(counter == 200){
				if(player.mana < 100 );
				player.mana += 10;
				if(player2.mana > 100  )
				player2.mana -= 10;
				counter = 0;
			}
			image(img, 0, 0, 900, 900);
			image(filler, 400, 0, 100, 50);

			player.yspeed = player.yspeed + gravity;
			player.y = player.y + player.yspeed;
			if (player.y >= 650) {
				player.yspeed = 0;
				player.y = 650;
			}

			player2.yspeed = player2.yspeed + gravity;
			player2.y = player2.y + player2.yspeed;
			if (player2.y >= 650) {
				player2.yspeed = 0;
				player2.y = 650;
			}
			fill(0, 255, 0);
			rect(health1.x, health1.y, health1.w, health1.h);
			fill(0, 255, 0);
			rect(health2.x, health2.y, health2.w, health2.h);
			// mana
			fill(0, 0, 225);
			rect(0, 50, player.mana, 50);
			fill(0, 0, 225);
			rect(700+player2.mana, 50, 100 , 50);

			boolean isHit = false;
			fill(139, 69, 19);

			image(p1CurrentImage, player.x, player.y);

			if (isHit == true) {
				fill(hitColor);
			} else {
				fill(140, 0, 172);
			}
			image(p2CurrentImage, player2.x, player2.y);

			if (player.x <= 0) {
				player.x = 0;
			}
			if (player2.x <= 0) {
				player2.x = 0;
			}
			if (player.x >= 850) {
				player.x = 850;
			}
			if (player2.x >= 850) {
				player2.x = 850;
			}

			if (player.y <= 50) {
				player.y = 75;
			}
			if (player2.y <= 50) {
				player2.y = 75;
			}

			for (int i = 0; i < specialList.size(); i++) {
				Specials hadoken = specialList.get(i);
				fill(0, 150, 175);
				rect(hadoken.x, hadoken.y, hadoken.w, hadoken.h);
				if (player.x <= player2.x) {
					hadoken.x = hadoken.x + hadoken.xspeed;
					System.out.println("X1 value " + hadoken.x + " , Speed1 "
							+ hadoken.xspeed);
					if (hadoken.x > 900) {
						specialList.remove(hadoken);
					}
				}
				if (player.x >= player2.x) {
					hadoken.x = hadoken.x - hadoken.xspeed;
					System.out.println("X1 value " + hadoken.x + " , Speed1 "
							+ hadoken.xspeed);
					if (hadoken.x < 0) {
						specialList.remove(hadoken);
					}
				}
				if (hadoken.isHitting(player2) && player2.isBlocking == false) {
					isHit = true;
					specialList.remove(hadoken);
					if (isHit = true) {
						fill(0, 175, 0);
						health2.x = health2.x + 20;
						System.out.println("Hit");
					}
				}

			}
			for (int k = 0; k < specialList2.size(); k++) {
				Specials2 hadoken2 = specialList2.get(k);
				fill(0, 150, 175);
				rect(hadoken2.x, hadoken2.y, hadoken2.w, hadoken2.h);
				if (player2.x >= player.x) {
					hadoken2.x = hadoken2.x - hadoken2.xspeed;
					System.out.println("X2 value " + hadoken2.x + " , Speed2 "
							+ hadoken2.xspeed);
					if (hadoken2.x < 0) {
						specialList2.remove(hadoken2);
					}
				}
				if (player2.x <= player.x) {
					hadoken2.x = hadoken2.x + hadoken2.xspeed;
					System.out.println("X2 value " + hadoken2.x + " , Speed2 "
							+ hadoken2.xspeed);
					if (hadoken2.x > 900) {
						specialList2.remove(hadoken2);
					}
				}
				if (hadoken2.isHitting(player)) {
					isHit = true;
					specialList2.remove(hadoken2);
					if (isHit = true) {
						fill(0, 175, 0);
						if (hadoken2.isHitting(player)
								&& player.isBlocking == false) {
							health1.w = health1.w - 20;
							System.out.println("Hit2");
						}
					}
				}
			}
			if (health1.w <= 0) {
				text("You win p2!", 450, 400);
				System.out.println("you win p2!");
				GameStart = false;
			}
			if (health2.x >= 900) {
				text("You win p1!", 450, 400);
				System.out.println("you win p1!");
				GameStart = false;
			}
		}

	}

	public void keyReleased() {
		if (key == 'w') {
			if (player.y >= 500){
			player.yspeed = -12;
			}

		}
		if (key == CODED) {
			if (keyCode == UP) {
				if (player2.y >= 500) {
					player2.yspeed = -12;
				}
			}
		}
	}

	public void keyPressed() {
		if (keyPressed) {
			// Player 1 Controls
			if (key == ' ') {
				player.mana -= 10;
				if (player.mana > 0) {
					player.isBlocking = false;
					p1CurrentImage = p1Hadoken;
					if (player.x <= player2.x) {
						Specials bob = new Specials(player.x + 50, player.y);
						specialList.add(bob);
					} else {
						Specials bob = new Specials(player.x, player.y);
						specialList.add(bob);
					}
				} else {
					player.mana += 10;
				}

			}

			if (key == 'q') {
				player.isBlocking = false;
				p1CurrentImage = image1Punch;
				if (player2.isHitting(player) && player2.isBlocking == false) {
					health2.x = health2.x + 40;
					System.out.println("Player 1 hit Player 2");
				}
			}
			if (key == 'e') {
				player.isBlocking = false;
				p1CurrentImage = image1Kick;
				if (player.isHitting(player2) && player2.isBlocking == false) {
					health2.x = health2.x + 55;
				}
			}
			if (key == 'a')
				player.x = player.x - 5;
			if (key == 'd')
				player.x = player.x + 5;
			if (key == 'x') {
				p1CurrentImage = image1Block;
				player.isBlocking = true;
				if (key != 'x') {
					player.isBlocking = false;
				}
			}

			// Player 2 Controls
			if (key == CODED) {
				if (keyCode == LEFT) {
					player2.x = player2.x - 5;
				} else if (keyCode == RIGHT) {
					player2.x = player2.x + 5;
				}
				if (key == 'd' && keyCode == LEFT) {
					player.x = player.x + 5;
					player2.x = player2.x - 5;
				}
				if (key == 'a' && keyCode == RIGHT) {
					player.x = player.x - 5;
					player2.x = player2.x + 5;
				}
			}
			if (key == '2') {
				p2CurrentImage = image2Block;
				player2.isBlocking = true;
				if (key != '2') {
					player2.isBlocking = false;
				}
			}
			if (key == 'l') {
				player2.mana += 10;
				if (player2.mana <190) {
				player2.isBlocking = false;
				p2CurrentImage = p2Hadoken;
				if (player2.x >= player.x) {
					Specials2 bill = new Specials2(player2.x - 10, player2.y);
					specialList2.add(bill);
				} else {
					Specials2 bill = new Specials2(player2.x + 10, player2.y);
					specialList2.add(bill);
				}
			}
			if (key == '1') {
					player2.isBlocking = false;
					p2CurrentImage = image2Punch;
					if (player2.isHitting(player) && player.isBlocking == false) {
						health1.w = health1.w - 40;
						System.out.println("Player 2 hit Player 1");
					}
				}
			}
			if (key == '3') {
				player2.isBlocking = false;
				p2CurrentImage = image2Kick;
				if (player2.isHitting(player) && player.isBlocking == false) {
					health1.w = health1.w - 55;
				}
			}

		}

	}

}