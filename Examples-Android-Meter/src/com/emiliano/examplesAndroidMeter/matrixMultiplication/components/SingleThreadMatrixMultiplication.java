package com.emiliano.examplesAndroidMeter.matrixMultiplication.components;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class SingleThreadMatrixMultiplication implements Component<MatrixPair, float[][]> {

	@Override
	public String getName() {
		return "Matrix Multiplication";
	}

	@Override
	public float[][] execute(MatrixPair input) {

		float[][] A = input.matrixA;
		float[][] B = input.matrixB;

		int aRows = A.length;
		int aColumns = A[0].length;
		int bRows = B.length;
		int bColumns = B[0].length;

		if (aColumns != bRows) {
			throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
		}

		float[][] C = new float[aRows][bColumns];

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
