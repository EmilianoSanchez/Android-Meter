package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.components.Component;

public class MatrixMultiplication implements Component<MatrixPair, double[][]> {

	@Override
	public String getName() {
		return "Matrix Multiplication";
	}

	@Override
	public double[][] execute(MatrixPair input) {

		double[][] A = input.matrixA;
		double[][] B = input.matrixB;

		int aRows = A.length;
		int aColumns = A[0].length;
		int bRows = B.length;
		int bColumns = B[0].length;

		if (aColumns != bRows) {
			throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
		}

		double[][] C = new double[aRows][bColumns];
		// for (int i = 0; i < aRows; i++) {
		// for (int j = 0; j < bColumns; j++) {
		// C[i][j] = 0.00000;
		// }
		// }

		for (int i = 0; i < aRows; i++) { // aRow
			for (int j = 0; j < bColumns; j++) { // bColumn
				for (int k = 0; k < aColumns; k++) { // aColumn
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return C;
	}

}
