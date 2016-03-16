package hr.fer.zemris.irg.vjezba1.task4.bresenham;

public class PointPair {

	public int xs;
	public int ys;

	public int xe;
	public int ye;

	public PointPair(int xs, int ys, int xe, int ye) {
		this.xs = xs;
		this.ys = ys;
		this.xe = xe;
		this.ye = ye;
	}

	public void swapPoints() {
		int tmp = xs;
		xs = xe;
		xe = tmp;
		tmp = ys;
		ys = ye;
		ye = tmp;
	}

	public void swapXYCoordinates() {
		int tmp = xe;
		xe = ye;
		ye = tmp;
		tmp = xs;
		xs = ys;
		ys = tmp;
	}

	public void swapStartEndXYCoordinates() {
		int tmp = xe;
		xe = ys;
		ys = tmp;
		tmp = xs;
		xs = ye;
		ye = tmp;
	}

}