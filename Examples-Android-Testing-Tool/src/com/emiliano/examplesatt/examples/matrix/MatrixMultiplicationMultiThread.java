package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.components.Component;

public class MatrixMultiplicationMultiThread implements Component<MatrixPair, double[][]> {

	private static final int MAX_THREADS = 8;

	@Override
	public String getName() {
		return "Matrix Multiplication MultiThread";
	}

	@Override
	public double[][] execute(MatrixPair input) {

		A = input.matrixA;
		B = input.matrixB;

		aRows = A.length;
		aColumns = A[0].length;
		bRows = B.length;
		bColumns = B[0].length;

		if (aColumns != bRows) {
			throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
		}

		C = new double[aRows][bColumns];

		// Log.i("TEST", "starting ");

		Thread[] threads = new Thread[MAX_THREADS];
		for (int i = 0; i < MAX_THREADS; i++) {
			threads[i] = new Thread(new MMThread(i));
			threads[i].start();
		}
		for (int i = 0; i < MAX_THREADS; i++) {
			try {
				threads[i].join();
				// Log.i("TEST", "holis");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return C;
	}

	private double[][] A;
	private double[][] B;
	private int aRows;
	private int aColumns;
	private int bRows;
	private int bColumns;
	private double[][] C;

	private class MMThread implements Runnable {
		private int module;

		public MMThread(int module) {
			this.module = module;
		}

		@Override
		public void run() {
			// Log.i("TEST", "starting module "+module);
			int i = module;
			while (i < aRows) {
				for (int j = 0; j < bColumns; j++) {
					for (int k = 0; k < aColumns; k++) {
						C[i][j] += A[i][k] * B[k][j];
					}
				}
				i += MAX_THREADS;
			}
			// Log.i("TEST", "finishing module "+module);
		}
	}
}
