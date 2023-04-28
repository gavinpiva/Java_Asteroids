
/*
CLASS: AsteroidsGame
DESCRIPTION: Extending Game, Asteroids is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.
Original code by Dan Leyzberg and Art Simon
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

public class Asteroids extends Game {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public Asteroid t, t2, t3;
	public Ship ship;
	public Star[] stars;
	static int counter = 0;
	public boolean colide = false;
	public int cs = 0;
	public int lives = 5;
	public boolean life = false;
	Random rand = new Random();

	private java.util.List<Asteroid> randomAsteroids = new ArrayList<Asteroid>();
	private ArrayList<Bullet> bull = new ArrayList<Bullet>();
	ArrayList<Asteroid> removedA = new ArrayList<Asteroid>();
	public Asteroids() {
		super("Asteroids!", SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setFocusable(true);
		this.requestFocus();

		// create a number of random asteroid objects
		randomAsteroids = createRandomAsteroids(10, 60, 30);

		ship = createShip();

		this.addKeyListener(ship);
		
		stars = createStars(200, 5);

	}

	private Ship createShip() {
		// Look of ship
		Point[] shipShape = { new Point(0, 0), new Point(Ship.SHIP_WIDTH / 3.5, Ship.SHIP_HEIGHT / 2),
				new Point(0, Ship.SHIP_HEIGHT), new Point(Ship.SHIP_WIDTH, Ship.SHIP_HEIGHT / 2) };
		// Set ship at the middle of the screen
		Point startingPosition = new Point((width - Ship.SHIP_WIDTH) / 2, (height - Ship.SHIP_HEIGHT) / 2);
		int startingRotation = 0; // Start facing to the right
		return new Ship(shipShape, startingPosition, startingRotation);
	}
		// creates random stars with random sizes
	public Star[] createStars(int numberOfStars, int maxRadius) {

		Star[] stars = new Star[numberOfStars];

		for (int i = 0; i < numberOfStars; ++i) {

			Point center = new Point

			(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);

			int radius = (int) (Math.random() * maxRadius);

			if (radius < 1) {

				radius = 1;

			}

			stars[i] = new Star(center, radius);

		}

		return stars;

	}

	// Create an array of random asteroids
	private java.util.List<Asteroid> createRandomAsteroids(int numberOfAsteroids, int maxAsteroidWidth,
			int minAsteroidWidth) {
		java.util.List<Asteroid> asteroids = new ArrayList<>(numberOfAsteroids);

		for (int i = 0; i < numberOfAsteroids; ++i) {
			// Create random asteroids by sampling points on a circle
			// Find the radius first.
			int radius = (int) (Math.random() * maxAsteroidWidth);
			if (radius < minAsteroidWidth) {
				radius += minAsteroidWidth;
			}
			// Find the circles angle
			double angle = (Math.random() * Math.PI * 1.0 / 2.0);
			if (angle < Math.PI * 1.0 / 5.0) {
				angle += Math.PI * 1.0 / 5.0;
			}
			// Sample and store points around that circle
			ArrayList<Point> asteroidSides = new ArrayList<Point>();
			double originalAngle = angle;
			while (angle < 2 * Math.PI) {
				double x = Math.cos(angle) * radius;
				double y = Math.sin(angle) * radius;
				asteroidSides.add(new Point(x, y));
				angle += originalAngle;
			}
			// Set everything up to create the asteroid
			Point[] inSides = asteroidSides.toArray(new Point[asteroidSides.size()]);
			Point inPosition = new Point(Math.random() * SCREEN_WIDTH, Math.random() * SCREEN_HEIGHT);
			double inRotation = Math.random() * 360;
			asteroids.add(new Asteroid(inSides, inPosition, inRotation));
		}
		return asteroids;
	}
	//paints eveything that you see on the screen
	public void paint(Graphics brush) {
		
		ArrayList<Bullet> removedB = new ArrayList<Bullet>();
		ArrayList<Asteroid> removedAsteroids = new ArrayList<Asteroid>();
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);

		// sample code for printing message for debugging
		// counter is incremented and this message printed
		// each time the canvas is repainted
		counter++;
		brush.setColor(Color.white);
		brush.drawString("Counter is " + counter, 10, 10);

		//displays the lives
		brush.setColor(Color.white);
		brush.drawString("Lives: "+ lives ,10,40);
		//creates random asteroids
		colide = false;
		cs--;
		for (Asteroid asteroid : randomAsteroids) {
			asteroid.paint(brush, Color.white);
			asteroid.move();
			if (asteroid.collision(ship) == true) {
				colide = true;
				cs = 100;
			}
		}
		//creates bullets on the screen
		bull = ship.getBullets();
		for(Bullet bullet : bull) {
			bullet.paint(brush, Color.green);
			bullet.move();
			if(bullet.outOfBounds() == true) {
				removedB.add(bullet);
			}
			//how to remove an asteroid and bullet when they colide
			for (Asteroid a : randomAsteroids) {
				if(a.contains(bullet.getCenter())) {
					removedAsteroids.remove(a);
					removedA.add(a);
					removedB.add(bullet);
				}
			}
	
			for(Asteroid y : removedA) {
					randomAsteroids.remove(y);
					
			}
			
			
			
		}
		for(Bullet b : removedB) {
			if(bull.contains(b))
				bull.remove(b);
		}
		ship.move();
		if (colide == true || cs > 0) {
			ship.paint(brush, Color.red);
		} else {
			ship.paint(brush, Color.cyan);
			colide = false;
		}
		if(cs == 99) {
			lives--;
		}
		
		//displays the stars on the screen
		for(int i = 0; i < stars.length; i++) {
			int n = rand.nextInt(stars.length);
			//stars[i].paint(brush, Color.white);
			if(n < 30) {
				stars[i].paint(brush, Color.black);
			}
			else {
				stars[i].paint(brush, Color.white);
			}
		}
		//conditions for ending game
		if(randomAsteroids.isEmpty()) {
			brush.setColor(Color.black);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.white);
			brush.drawString("You have won the game!!!",10,10);
		}
		if(lives <= 0) {
			brush.setColor(Color.black);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.white);
			brush.drawString("You have lost the game!!!",10,10);
		}
	}
	
	
	

	public static void main(String[] args) {
		Asteroids a = new Asteroids();
		a.repaint();

	}
}
