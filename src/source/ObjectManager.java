package source;

import java.awt.Graphics;

public class ObjectManager {
Rocketship rs2;

ObjectManager(Rocketship rs2) {
	this.rs2 = rs2;
}
void update() {
	rs2.update();
}
void draw(Graphics g) {
	rs2.draw(g);
}
}
