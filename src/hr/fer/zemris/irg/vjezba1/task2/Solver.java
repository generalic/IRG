package hr.fer.zemris.irg.vjezba1.task2;

import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Solver {

	private RealMatrix coefficients;
	private RealVector constants;

	public Solver(RealMatrix coefficients, RealVector constants) {
		super();
		this.coefficients = coefficients;
		this.constants = constants;
	}

	public RealVector solve() {
		DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
		return solver.solve(constants);
	}

}
