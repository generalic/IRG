package hr.fer.zemris.irg.vjezba1.task4;

import java.util.function.BiConsumer;

public class Bresenham {

	private int xs;
	private int ys;

	private int xe;
	private int ye;

	public Bresenham(int xs, int ys, int xe, int ye) {
		super();
		this.xs = xs;
		this.ys = ys;
		this.xe = xe;
		this.ye = ye;
	}

	public void drawLine(IPixelManager pixelManager) {
		if(xs <= xe) {
			if(ys <= ye) {
				bresenhamPositive(pixelManager);
			} else {
				bresenhamNegative(pixelManager);
			}
		} else {
			replaceStartEndPoints();
			if(ys >= ye) {
				bresenhamPositive(pixelManager);
			} else {
				bresenhamNegative(pixelManager);
			}
		}
//		if(xs <= xe) {
//			if(ys <= ye) {
//				bresenhamPositive(pixelManager);
//			} else {
//				bresenhamNegative(pixelManager);
//			}
//		} else {
//			replaceStartEndPoints();
//			if(ys >= ye) {
//				bresenhamPositive(pixelManager);
//			} else {
//				bresenhamNegative(pixelManager);
//			}
//		}
	}

	private void bresenham3Strategy(BiConsumer<Integer, Integer> pixelManager) {
		new AbstractBresenhamStrategy((xe - xs), 2 * (xe - xs)) {

			@Override
			protected boolean checkOffset(int yf) {
				return yf <= 0;
			}

			@Override
			protected int calculateYc(int yc) {
				return yc - 1;
			}
		}.draw(pixelManager);
	}

	private void bresenham2Strategy(BiConsumer<Integer, Integer> pixelManager) {
		new AbstractBresenhamStrategy(-(xe - xs), -2 * (xe - xs)) {

			@Override
			protected boolean checkOffset(int yf) {
				return yf >= 0;
			}

			@Override
			protected int calculateYc(int yc) {
				return yc + 1;
			}
		}.draw(pixelManager);
	}

	private abstract class AbstractBresenhamStrategy {

		private int a = 2 * (ye - ys);
		private int yc = ys;

		private int yf;
		private int korekcija;

		public AbstractBresenhamStrategy(int yf, int korekcija) {
			this.yf = yf;
			this.korekcija = korekcija;
		}

		public final void draw(BiConsumer<Integer, Integer> pixelManager) {
			for (int x = xs; x <= xe; x++) {
				pixelManager.accept(x, yc);
				yf = yf + a;
				if (checkOffset(yf)) {
					yf = yf + korekcija;
					yc = calculateYc(yc);
				}
			}
		}

		protected abstract boolean checkOffset(int yf);

		protected abstract int calculateYc(int yc);

	}

	private void replaceStartEndPoints() {
		int x = xs;
		xs = xe;
		xe = x;
		x = ys;
		ys = ye;
		ye = x;
	}

	private void replaceXYCoordinates() {
		int x = xe;
		xe = ye;
		ye = x;
		x = xs;
		xs = ys;
		ys = x;
	}

	private void bresenhamPositive(IPixelManager pixelManager) {
		if ((ye - ys) <= xe - xs) {
			bresenham2Strategy((x, yc) -> pixelManager.turnPixelOn(x, yc));
		} else {
			replaceXYCoordinates();
			bresenham2Strategy((x, yc) -> pixelManager.turnPixelOn(yc, x));
		}
	}

	private void bresenhamNegative(IPixelManager pixelManager) {
		if (-(ye - ys) <= xe - xs) {
			bresenham3Strategy((x, yc) -> pixelManager.turnPixelOn(x, yc));
		} else {
			replaceXYCoordinates();
			bresenham3Strategy((x, yc) -> pixelManager.turnPixelOn(yc, x));
		}
	}

	private void bresenham_nacrtaj_cjelobrojni2(int xs, int ys, int xe, int ye) {
		int x, yc, korekcija;
		int a, yf;

		if ((ye - ys) <= xe - xs) {
			a = 2 * (ye - ys);
			yc = ys;
			yf = -(xe - xs);
			korekcija = -2 * (xe - xs);

			for (x = xs; x <= xe; x++) {
				// osvijetli_pixel(x, yc);
				yf = yf + a;
				if (yf >= 0) {
					yf = yf + korekcija;
					yc = yc + 1;
				}
			}
		} else {
			// zamijeni x i y koordinate
			x = xe;
			xe = ye;
			ye = x;
			x = xs;
			xs = ys;
			ys = x;

			a = 2 * (ye - ys);
			yc = ys;
			yf = -(xe - xs);
			korekcija = -2 * (xe - xs);

			for (x = xs; x <= xe; x++) {
				// osvijetli_pixel(yc, x);
				yf = yf + a;
				if (yf >= 0) {
					yf = yf + korekcija;
					yc = yc + 1;
				}
			}
		}
	}

	private void bresenham_nacrtaj_cjelobrojni3(int xs, int ys, int xe, int ye) {
		int x, yc, korekcija;
		int a, yf;

		if (-(ye - ys) <= xe - xs) {
			a = 2 * (ye - ys);
			yc = ys;
			yf = (xe - xs);
			korekcija = 2 * (xe - xs);

			for (x = xs; x <= xe; x++) {
				// osvijetli_pixel(x, yc);
				yf = yf + a;
				if (yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		} else {
			// zamijeni x i y koordinate
			x = xe;
			xe = ye;
			ye = x;
			x = xs;
			xs = ys;
			ys = x;

			a = 2 * (ye - ys);
			yc = ys;
			yf = (xe - xs);
			korekcija = 2 * (xe - xs);

			for (x = xs; x <= xe; x++) {
				// osvijetli_pixel(yc, x);
				yf = yf + a;
				if (yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		}
	}


}
