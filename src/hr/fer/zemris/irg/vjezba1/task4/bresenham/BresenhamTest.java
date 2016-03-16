package hr.fer.zemris.irg.vjezba1.task4.bresenham;

import hr.fer.zemris.irg.vjezba1.task4.IPixelManager;

public class BresenhamTest {

	private IPixelManager pixelManager;

	public BresenhamTest(IPixelManager pixelManager) {
		super();
		this.pixelManager = pixelManager;
	}

	public void drawLine(int xs, int ys, int xe, int ye) {
		if(xs <= xe) {
			if(ys <= ye) {
				bresenham_nacrtaj_cjelobrojni2(xs, ys, xe, ye);
			} else {
				bresenham_nacrtaj_cjelobrojni3(xs, ys, xe, ye);
			}
		} else {
			if(ys >= ye) {
				bresenham_nacrtaj_cjelobrojni2(xe, ye, xs, ys);
			} else {
				bresenham_nacrtaj_cjelobrojni3(xe, ye, xs, ys);
			}
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
				pixelManager.turnPixelOn(x, yc);
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
				pixelManager.turnPixelOn(yc, x);
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
				pixelManager.turnPixelOn(x, yc);
				yf = yf + a;
				if (yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		} else {
			// zamijeni x i y koordinate
			x = xe;
			xe = ys;
			ys = x;
			x = xs;
			xs = ye;
			ye = x;

			a = 2 * (ye - ys);
			yc = ys;
			yf = (xe - xs);
			korekcija = 2 * (xe - xs);

			for (x = xs; x <= xe; x++) {
				// osvijetli_pixel(yc, x);
				pixelManager.turnPixelOn(yc, x);
				yf = yf + a;
				if (yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		}
	}


}
