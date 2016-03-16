package hr.fer.zemris.irg.vjezba1.task4;

import java.awt.Point;

public class Line {

	public int xs;
	public int ys;

	public int xe;
	public int ye;

	public Line(int xs, int ys, int xe, int ye) {
		super();
		this.xs = xs;
		this.ys = ys;
		this.xe = xe;
		this.ye = ye;
	}

	public Line(Point start, Point end) {
		this(start.x, start.y, end.x, end.y);
	}

	@Override
	public String toString() {
		return "LINE " + xs + " " + ys + " " + xe + " " + ye;
	}

}
