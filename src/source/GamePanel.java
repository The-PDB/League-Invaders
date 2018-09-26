package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final int MENU_STATE = 0;

	final int GAME_STATE = 1;

	final int END_STATE = 2;

	Font titleFont;

	Font subFont;

	Rocketship rs = new Rocketship(250, 700, 50, 50);

	ObjectManager manager = new ObjectManager(rs);

	int CURRENT_STATE = MENU_STATE;

	@Override

	public void paintComponent(Graphics g) {
		if (CURRENT_STATE == MENU_STATE) {
			drawMenuState(g);
		} else if (CURRENT_STATE == GAME_STATE) {
			drawGameState(g);
		} else if (CURRENT_STATE == END_STATE) {
			drawEndState(g);
		}

	}

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subFont = new Font("Arial", Font.PLAIN, 24);

	}

	void startGame() {
		timer.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		manager.manageEnemies();
		manager.checkCollision();
		manager.purgeObjects();
		if (!rs.isAlive) {
			CURRENT_STATE = END_STATE;

		}
		if (up) {
			rs.y -= rs.speed;
		}
		if (down) {
			rs.y += rs.speed;
		}
		if (right) {
			rs.x += rs.speed;
		}
		if (left) {
			rs.x -= rs.speed;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("LEAGUE INVADERS", 25, 200);
		g.setFont(subFont);
		g.drawString("Press ENTER to start", 125, 400);
		g.drawString("Press SPACE for instructions", 87, 600);

	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		manager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 200);
		g.setFont(subFont);
		g.drawString("You killed " + "enemies", 137, 400);
		g.drawString("Press ENTER to restart", 100, 600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if (CURRENT_STATE == MENU_STATE) {
			updateMenuState();
		} else if (CURRENT_STATE == GAME_STATE) {
			updateGameState();
		} else if (CURRENT_STATE == END_STATE) {
			updateEndState();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	boolean up = false, right = false, down = false, left = false;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (CURRENT_STATE == END_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				CURRENT_STATE = 0;
			}
		}

		else if (CURRENT_STATE == MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				CURRENT_STATE = 1;

			}
		} else if (CURRENT_STATE == GAME_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				CURRENT_STATE = 2;

			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				Projectile p1 = new Projectile(rs.x, rs.y, 10, 10);
				manager.addProjectile(p1);

			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
	}
}
