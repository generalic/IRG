import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class Test {

	public static void main(String[] args) {

		double[][] matrixData = { {1, 2, 3}, {2, 5, 3} };
		RealMatrix m = MatrixUtils.createRealMatrix(matrixData);

		double[][] data2 = { {1, 2}, {3, 4}, {5, 6} };
		RealMatrix n = new Array2DRowRealMatrix(data2);

		RealMatrix p = m.multiply(n);

		System.out.println(p.transpose());
		System.out.println(p);

		System.out.println(new LUDecomposition(p).getDeterminant());
		System.out.println(new LUDecomposition(p).getSolver().getInverse());


		RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { { 2, 3, -2 }, { -1, 7, 6 }, { 4, -3, -5 } });
		DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();

		RealVector constants = new ArrayRealVector(new double[] { 1, -2, 1 });
		RealVector solution = solver.solve(constants);

		System.out.println(solution);

	}

}
