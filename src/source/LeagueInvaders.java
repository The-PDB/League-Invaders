package source;

import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame f;
	static final int width = 500;
	static final int height = 800;
	GamePanel gp;
public static void main(String[] args) {
	LeagueInvaders lv = new LeagueInvaders();
	lv.setup();
}
LeagueInvaders() {
	f = new JFrame();
	f.setVisible(true);
	f.setSize(width, height);
	gp = new GamePanel();
	f.addKeyListener(gp);
}
void setup() {
	f.add(gp);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.getContentPane().setPreferredSize(new Dimension(width, height));
    f.pack();
    gp.startGame();
}
}
