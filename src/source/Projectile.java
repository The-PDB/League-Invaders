package source;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed = 10;

	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
		y-=5;
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.bulletImg, x + 20, y, width, height, null);
	}

}
