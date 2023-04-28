import java.awt.Color;
import java.awt.Graphics;

public class Star extends Circle {

	public Star(Point center, int radius) {
		super(center, radius);
		// TODO Auto-generated constructor stub
	}

	@Override
	//paint the star
	public void paint(Graphics brush, Color color) {
		brush.setColor(color);
		brush.fillOval((int)super.center.x, (int)super.center.y, radius, radius);
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

		
}
