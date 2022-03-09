package mountain;

import fractal.*;
import java.util.*;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private double dev;
	private HashMap<Side, Point> map;

	public Mountain(Point a, Point b, Point c, double dev) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		map = new HashMap<Side, Point>();
	}

	@Override
	public String getTitle() {

		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics g) {
		fractalTriangle(g, order, a, b, c, dev);

	}

	private Point avgPoint(Point a, Point b, double dev) {
		int x = (a.getX() + b.getX()) / 2;
		int y = (int) ((a.getY() + b.getY()) / 2 + RandomUtilities.randFunc(dev));
		return new Point(x, y);
	}

	private Point newAvgPoint(Point a, Point b, double dev) {
		Side s = new Side(a, b);
		if (map.containsKey(s)) {
			Point ptemp = map.get(s);
			map.remove(s);
			return ptemp;
		} else {
			Point newPoint = avgPoint(a, b, dev);
			map.put(s, newPoint);
			return newPoint;
		}

	}

	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {

		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());

			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			Point ab = newAvgPoint(a, b, dev);
			Point bc = newAvgPoint(b, c, dev);
			Point ac = newAvgPoint(c, a, dev);

			fractalTriangle(turtle, order - 1, a, ab, ac, dev / 2);
			fractalTriangle(turtle, order - 1, ab, b, bc, dev / 2);
			fractalTriangle(turtle, order - 1, ac, bc, c, dev / 2);
			fractalTriangle(turtle, order - 1, ac, ab, bc, dev / 2);

		}

	}
}
