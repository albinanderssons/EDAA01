package mountain;

public class Side {

	Point p1;
	Point p2;

	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public boolean equals(Object a) {
		Side s = (Side) a;
		if (p1 == s.p1 && p2 == s.p2 || p1 == s.p2 && p2 == s.p1) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}

}
