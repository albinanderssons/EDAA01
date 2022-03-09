package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(300);
		fractals[0] = new Mountain(new Point(100, 400), new Point(250, 200), new Point(500, 450), 20);
		new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
