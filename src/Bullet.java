import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Bullet extends Circle {
	private static final int RADIUS = 6;
	public boolean firing;
	double rotation;
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public Bullet(Point center, double rotation) {

		super(center, RADIUS); // define RADIUS in Bullet class
		this.rotation = rotation;
		
		}

	@Override
	//paints the bullet on the screen
	public void paint(Graphics brush, Color color) {
		brush.setColor(color);
		brush.fillOval((int)center.x, (int)center.y, RADIUS, RADIUS);
		
	}

	@Override
	//method that moves the bullet
	public void move() {
		// TODO Auto-generated method stub
			center.x += 5 * Math.cos(Math.toRadians(rotation));
			center.y += 5 * Math.sin(Math.toRadians(rotation));
	}
	//method that sees weather the bullet is out of bounds and removes it
	public boolean outOfBounds() {
		boolean out = false;
		if (center.x > Asteroids.SCREEN_WIDTH) {
			center.x = Asteroids.SCREEN_WIDTH % Asteroids.SCREEN_WIDTH;
			out = true;
		}
		if (center.y > Asteroids.SCREEN_HEIGHT) {
			center.y = Asteroids.SCREEN_HEIGHT % Asteroids.SCREEN_HEIGHT;
			out = true;
		}
		if(center.x < 0) {
			center.x = Asteroids.SCREEN_WIDTH;
			out = true;
		}
		if(center.y < 0) {
			center.y = Asteroids.SCREEN_HEIGHT;
			out = true;
		}
		return out;
	}
	//return the center of the bullet
	public Point getCenter() {
		return this.center;
	}
}
