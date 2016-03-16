package hr.fer.zemris.irg.vjezba1.task4;

import java.awt.Point;

public class Line {

	private Point start;
	private Point end;

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public void draw() {

	}

	@Override
	public String toString() {
		return "LINE " + start.x + " " + start.y + " " + end.x + " " + end.y;
	}

}
