package hr.fer.zemris.irg.vjezba1.task1;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

public class BasicOperations {

	public static void main(String[] args) {

		/* VECTOR OPERATIONS */
		Vector3D t1 = new Vector3D(2, 3, -4);
		Vector3D t2 = new Vector3D(-1, 4, -1);

		Vector3D v1 = t1.add(t2);
		System.out.println(v1);

		double s = v1.dotProduct(new Vector3D(-1, 4, -1));
		System.out.println(s);

		Vector3D v2 = v1.crossProduct(new Vector3D(2, 2, 4));
		System.out.println(v2);

		Vector3D v3 = v2.normalize();
		System.out.println(v3);

		Vector3D v4 = v2.negate();
		System.out.println(v4);

		/* MATRIX OPERATIONS */
		double[][] data1 = { {1, 2, 3}, {2, 1, 3}, {4, 5, 1} };
		RealMatrix matrix1 = new Array2DRowRealMatrix(data1);

		double[][] data2 = { {-1, 2, -3}, {5, -2, 7}, {-4, -1, 3} };
		RealMatrix matrix2 = new Array2DRowRealMatrix(data2);

		RealMatrix m1 = matrix1.add(matrix2);
		System.out.println(m1);

		RealMatrix m2 = matrix1.multiply(matrix2.transpose());
		System.out.println(m2);

		RealMatrix m3 = matrix1.multiply(new LUDecomposition(matrix2).getSolver().getInverse());
		System.out.println(m3);

	}

}
