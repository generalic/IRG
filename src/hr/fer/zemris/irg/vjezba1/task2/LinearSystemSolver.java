package hr.fer.zemris.irg.vjezba1.task2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class LinearSystemSolver {

	private static final int ROW_LIMIT = 3;
	private static final int COL_LIMIT = 3;

	public static void main(String[] args) {
		args = new String[1];
		args[0] = "1, 1, 1, 6, -1, -2, 1, -2, 2, 1, 3, 13";

		try {
			run(args[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void run(String data) {

		double[][] coefficientsData = new double[ROW_LIMIT][COL_LIMIT];
		double[] constantsData = new double[COL_LIMIT];

		List<Double> list = Arrays.stream(data.split(","))
			.map(Double::parseDouble)
			.collect(Collectors.toList());

		int index = 0;
		for(int i = 0; i < ROW_LIMIT; i++) {
			for(int j = 0; j < COL_LIMIT; j++) {
				coefficientsData[i][j] = list.get(index++);
				if((index + 1) % 4 == 0) {
					constantsData[i] = list.get(index++);
				}
			}
		}

		RealMatrix coefficients = new Array2DRowRealMatrix(coefficientsData);
		System.out.println(coefficients);

		RealVector constants = new ArrayRealVector(constantsData);
		System.out.println(constants);

		RealVector solution = new Solver(coefficients, constants).solve();
		System.out.println(solution);

	}

}
