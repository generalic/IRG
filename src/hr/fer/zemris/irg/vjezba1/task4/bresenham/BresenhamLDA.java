package hr.fer.zemris.irg.vjezba1.task4.bresenham;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import hr.fer.zemris.irg.vjezba1.task4.IPixelManager;
import hr.fer.zemris.irg.vjezba1.task4.Line;

public class BresenhamLDA {

	private IPixelManager pixelManager;
	private PointPair p;

	public BresenhamLDA(IPixelManager pixelManager) {
		super();
		this.pixelManager = pixelManager;
	}

	public void drawLine(int xs, int ys, int xe, int ye) {
		this.p = new PointPair(xs, ys, xe, ye);
		if(xs <= xe) {
			if(ys <= ye) {
				bresenhamPositive();
			} else {
				bresenhamNegative();
			}
		} else {
			p.swapPoints();
			if(ys >= ye) {
				bresenhamPositive();
			} else {
				bresenhamNegative();
			}
		}
	}

	public void drawLine(Line line) {
		drawLine(line.xs, line.ys, line.xe, line.ye);
	}

	private abstract class AbstractBresenhamDrawing {

		private int a = 2 * (p.ye - p.ys);
		private int yc = p.ys;

		private int yf;
		private int korekcija;

		public AbstractBresenhamDrawing(int yf, int korekcija) {
			this.yf = yf;
			this.korekcija = korekcija;
		}

		public final void draw(BiConsumer<Integer, Integer> pixelManager) {
			for (int x = p.xs; x <= p.xe; x++) {
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

	private void bresenhamPositiveStrategy(BiConsumer<Integer, Integer> pixel) {
		new AbstractBresenhamDrawing(-(p.xe - p.xs), -2 * (p.xe - p.xs)) {

			@Override
			protected boolean checkOffset(int yf) {
				return yf >= 0;
			}

			@Override
			protected int calculateYc(int yc) {
				return yc + 1;
			}
		}.draw(pixel);
	}

	private void bresenhamNegativeStrategy(BiConsumer<Integer, Integer> pixel) {
		new AbstractBresenhamDrawing((p.xe - p.xs), 2 * (p.xe - p.xs)) {

			@Override
			protected boolean checkOffset(int yf) {
				return yf <= 0;
			}

			@Override
			protected int calculateYc(int yc) {
				return yc - 1;
			}
		}.draw(pixel);
	}

	private void bresenhamPositive() {
		bresenhamStrategy((p.ye - p.ys) <= p.xe - p.xs, this::bresenhamPositiveStrategy, PointPair::swapXYCoordinates);
	}

	private void bresenhamNegative() {
		bresenhamStrategy(-(p.ye - p.ys) <= p.xe - p.xs, this::bresenhamNegativeStrategy,
				PointPair::swapStartEndXYCoordinates);
	}

	private void bresenhamStrategy(boolean condition, Consumer<BiConsumer<Integer, Integer>> strategyConsumer,
			Consumer<PointPair> pointConsumer) {
		if (condition) {
			strategyConsumer.accept((x, yc) -> pixelManager.turnPixelOn(x, yc));
		} else {
			pointConsumer.accept(p);
			strategyConsumer.accept((x, yc) -> pixelManager.turnPixelOn(yc, x));
		}
	}

//	private void bresenham_nacrtaj_cjelobrojni2() {
//	int x, yc, korekcija;
//	int a, yf;
//
//	if ((p.ye - p.ys) <= p.xe - p.xs) {
//		a = 2 * (p.ye - p.ys);
//		yc = p.ys;
//		yf = -(p.xe - p.xs);
//		korekcija = -2 * (p.xe - p.xs);
//
//		for (x = p.xs; x <= p.xe; x++) {
//			// osvijetli_pixel(x, yc);
//			pixelManager.turnPixelOn(x, yc);
//			yf = yf + a;
//			if (yf >= 0) {
//				yf = yf + korekcija;
//				yc = yc + 1;
//			}
//		}
//	} else {
//		// zamijeni x i y koordinate
//		p.swapXYCoordinates();
//
//		a = 2 * (p.ye - p.ys);
//		yc = p.ys;
//		yf = -(p.xe - p.xs);
//		korekcija = -2 * (p.xe - p.xs);
//
//		for (x = p.xs; x <= p.xe; x++) {
//			// osvijetli_pixel(yc, x);
//			pixelManager.turnPixelOn(yc, x);
//			yf = yf + a;
//			if (yf >= 0) {
//				yf = yf + korekcija;
//				yc = yc + 1;
//			}
//		}
//	}
//}
//
//private void bresenham_nacrtaj_cjelobrojni3() {
//	int x, yc, korekcija;
//	int a, yf;
//
//	if (-(p.ye - p.ys) <= p.xe - p.xs) {
//		a = 2 * (p.ye - p.ys);
//		yc = p.ys;
//		yf = (p.xe - p.xs);
//		korekcija = 2 * (p.xe - p.xs);
//
//		for (x = p.xs; x <= p.xe; x++) {
//			// osvijetli_pixel(x, yc);
//			pixelManager.turnPixelOn(x, yc);
//			yf = yf + a;
//			if (yf <= 0) {
//				yf = yf + korekcija;
//				yc = yc - 1;
//			}
//		}
//	} else {
//		// zamijeni x i y koordinate
//		p.swapStartEndXYCoordinates();
//
//		a = 2 * (p.ye - p.ys);
//		yc = p.ys;
//		yf = (p.xe - p.xs);
//		korekcija = 2 * (p.xe - p.xs);
//
//		for (x = p.xs; x <= p.xe; x++) {
//			// osvijetli_pixel(yc, x);
//			pixelManager.turnPixelOn(yc, x);
//			yf = yf + a;
//			if (yf <= 0) {
//				yf = yf + korekcija;
//				yc = yc - 1;
//			}
//		}
//	}
//}

}
