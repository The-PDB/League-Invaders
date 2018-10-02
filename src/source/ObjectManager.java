package source;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	Rocketship rs2;
	ArrayList<Projectile> pList = new ArrayList<Projectile>();
	ArrayList<Alien> aList = new ArrayList<Alien>();

	long enemyTimer = 0;
	int enemySpawnTime = 2500;
	int score = 0;

	public int getScore() {
		return score;
	}
	

	ObjectManager(Rocketship rs2) {
		this.rs2 = rs2;
	}

	void update() {
		rs2.update();
		for (Projectile p : pList) {
			p.update();
		}
		for (Alien a : aList) {
			a.update();
		}
	}

	void draw(Graphics g) {
		rs2.draw(g);
		for (Projectile p : pList) {
			p.draw(g);
		}
		for (Alien a : aList) {
			a.draw(g);
		}
	}

	void addProjectile(Projectile p) {
		this.pList.add(p);
	}

	void addAlien(Alien a) {
		this.aList.add(a);
	}

	public void manageEnemies() {
		
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addAlien(new Alien(new Random().nextInt(LeagueInvaders.width - 50), 0, 50, 50));

			enemyTimer = System.currentTimeMillis();
		}
	}
	void purgeObjects() {
		for (int i = 0; i < aList.size(); i++) {
			if(!aList.get(i).isAlive) {
				aList.remove(i);
			}
		}
		for (int i = 0; i < pList.size(); i++) {
			if(!pList.get(i).isAlive) {
				pList.remove(i);
			}
		}
	}
	void checkCollision() {
		for(Alien a : aList){
	        if(rs2.collisionBox.intersects(a.collisionBox)){
	                rs2.isAlive = false;
	        }
	        for(Projectile p : pList) {
	        if(p.collisionBox.intersects(a.collisionBox)) {
	        		a.isAlive = false;
	        		score++;
	        		p.isAlive = false;
	        }

	}
		
		}
	}

}
