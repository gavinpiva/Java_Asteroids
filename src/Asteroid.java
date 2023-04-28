/**
 * Asteroid.java
 * 
 * Class that represents an Asteroid object
 */
import java.awt.Color;
import java.awt.Graphics;

public class Asteroid extends Polygon {


	public Asteroid(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}



	@Override
	//the paint method is how the asteroid is put onto the screen
	public void paint(Graphics brush, Color color) {
		Point[] pts = getPoints();
		int[] xpts = new int[pts.length];
		int[] ypts = new int[pts.length];
		int npts = pts.length;

		for (int i = 0; i < npts; i++) {
			xpts[i] = (int)pts[i].x;
			ypts[i] = (int)pts[i].y;
		}

		brush.setColor(color);
		brush.drawPolygon(xpts, ypts, npts);

	}

	@Override
	//how the asteroid is able to move on the screen
	public void move() {
		position.x += Math.cos(Math.toRadians(rotation));
		position.y += Math.sin(Math.toRadians(rotation));
		if (this.position.x > Asteroids.SCREEN_WIDTH) {
			position.x = Asteroids.SCREEN_WIDTH % Asteroids.SCREEN_WIDTH;
		}
		if (this.position.y > Asteroids.SCREEN_HEIGHT) {
			position.y = Asteroids.SCREEN_HEIGHT % Asteroids.SCREEN_HEIGHT;
		}
		if(this.position.x < 0) {
			position.x = Asteroids.SCREEN_WIDTH;
		}
		if(this.position.y < 0) {
			position.y = Asteroids.SCREEN_HEIGHT;
		}

	}

}

