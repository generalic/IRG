package hr.fer.zemris.irg.vjezba1.task4.bresenham;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import hr.fer.zemris.irg.vjezba1.task4.IPixelManager;

public class BresenhamLDAFunctionalTest {

	private IPixelManager pixelManager;
	private PointPair p;

	private static class PointPair {

		private int xs;
		private int ys;

		private int xe;
		private int ye;

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

	public BresenhamLDAFunctionalTest(IPixelManager pixelManager) {
		super();
		this.pixelManager = pixelManager;
	}

	public void drawLine(int xs, int ys, int xe, int ye) {
		this.p = new PointPair(xs, ys, xe, ye);
		if (!(xs <= xe)) {
			p.swapPoints();
		}
		if (ys <= ye) {
			bresenhamPositive();
		} else {
			bresenhamNegative();
		}
	}

	private void draw(int yf, int korekcija, BiConsumer<Integer, Integer> pixel, Predicate<Integer> condition,
			Function<Integer, Integer> yModifier) {
		int a = 2 * (p.ye - p.ys);
		int yc = p.ys;

		for (int x = p.xs; x <= p.xe; x++) {
			// osvijetli_pixel(x, yc);
			pixel.accept(x, yc);
			yf = yf + a;
			if (condition.test(yf)) {
				yf = yf + korekcija;
				yc = yModifier.apply(yc);
			}
		}
	}

	private void bresenhamPositiveStrategy(BiConsumer<Integer, Integer> pixel) {
		draw(-(p.xe - p.xs), -2 * (p.xe - p.xs), pixel, yf -> yf >= 0, yc -> yc + 1);
	}

	private void bresenhamNegativeStrategy(BiConsumer<Integer, Integer> pixel) {
		draw((p.xe - p.xs), 2 * (p.xe - p.xs), pixel, yf -> yf <= 0, yc -> yc - 1);
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
