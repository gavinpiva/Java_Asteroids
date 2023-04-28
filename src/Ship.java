import java.awt.Color;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Graphics;

public class Ship extends Polygon implements java.awt.event.KeyListener {
	public static final int SHIP_WIDTH = 40;
	public static final int SHIP_HEIGHT = 25;
	public boolean forward, left, right, firing = false;
	public boolean released = true;
	public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public Ship(Point[] shipDesign, Point placement, double inRotation) {
		super(shipDesign, placement, inRotation);
	}

	@Override
	//method that point the ship on the screen
	public void paint(Graphics brush, Color color) {
		Point[] points = getPoints();
		int[] xPoints = new int[points.length];
		int[] yPoints = new int[points.length];
		int nPoints = points.length;
		for (int i = 0; i < nPoints; ++i) {
			xPoints[i] = (int) points[i].x;
			yPoints[i] = (int) points[i].y;
		}
		brush.setColor(color);
		brush.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	//method that moves the ships
	public void move() {
		if (forward == true) {
			position.x += 3 * Math.cos(Math.toRadians(rotation));
			position.y += 3 * Math.sin(Math.toRadians(rotation));
		}
		if (left == true) {
			this.rotate(-3);
		}
		if (right == true) {
			this.rotate(3);
		}
		if (firing == true) {
			Point[] front = super.getPoints();
			Bullet bul = new Bullet(front[3], super.rotation);
			bullets.add(bul);
			firing = false;
		}
		if (this.position.x > Asteroids.SCREEN_WIDTH) {
			position.x = Asteroids.SCREEN_WIDTH % Asteroids.SCREEN_WIDTH;
		}
		if (this.position.y > Asteroids.SCREEN_HEIGHT) {
			position.y = Asteroids.SCREEN_HEIGHT % Asteroids.SCREEN_HEIGHT;
		}
		if (this.position.x < 0) {
			position.x = Asteroids.SCREEN_WIDTH;
		}
		if (this.position.y < 0) {
			position.y = Asteroids.SCREEN_HEIGHT;
		}
	}

	@Override
	//method that activated when the key is pressed in
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			forward = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (released == true) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firing = true;
				released = false;
			}
		}
	}

	@Override
	//method activated when the key is unreleased
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			forward = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
			// this.rotate(-5);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
			// this.rotate(5);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			released = true;
			firing = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		return;
	}
	//return the list of bullets
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

}
