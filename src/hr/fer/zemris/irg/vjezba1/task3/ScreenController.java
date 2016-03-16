package hr.fer.zemris.irg.vjezba1.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import hr.fer.zemris.irg.vjezba1.task2.Solver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ScreenController {

	private static final int MATRIX_DIMENSION = 3;

	@FXML
	private Button calculateButton;

	@FXML
	private TextField aCoordinatesTextField;

	@FXML
	private TextField bCoordinatesTextField;

	@FXML
	private TextField cCoordinatesTextField;

	@FXML
	private TextField tCoordinatesTextField;

	@FXML
	private Text solutionCoordinatesText;

	@FXML
	private void calculateCoordinates() {
		String text;
		try {
			RealMatrix coefficients = createCoefficients();
			RealVector constants = createConstants();
			RealVector solution = new Solver(coefficients, constants).solve();

			text = Arrays.toString(solution.toArray());
			text = text.substring(1, text.length() - 1);
		} catch (Exception e) {
			text = e.getMessage();
		}
		solutionCoordinatesText.setText(text);
	}

	private RealVector createConstants() {
		double[] constantsData = parseCoordinates(tCoordinatesTextField.getText());
		return new ArrayRealVector(constantsData);
	}

	private RealMatrix createCoefficients() {
		List<double[]> coefficientRows = new ArrayList<>();
		coefficientRows.add(parseCoordinates(aCoordinatesTextField.getText()));
		coefficientRows.add(parseCoordinates(bCoordinatesTextField.getText()));
		coefficientRows.add(parseCoordinates(cCoordinatesTextField.getText()));

		double[][] coefficientsData = new double[MATRIX_DIMENSION][MATRIX_DIMENSION];
		for(int i = 0; i < MATRIX_DIMENSION; i++) {
			for(int j = 0; j < MATRIX_DIMENSION; j++) {
				coefficientsData[i][j] = coefficientRows.get(i)[j];
			}
		}

		return new Array2DRowRealMatrix(coefficientsData);
	}

	private double[] parseCoordinates(String coordinates) {
		return Arrays.stream(coordinates.split(","))
				.mapToDouble(Double::parseDouble)
				.toArray();
	}

}
