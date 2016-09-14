package com.emiliano.examplesAndroidMeter.matrixMultiplication.components;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class NativeMatrixMultiplication implements Component<MatrixPair, float[][]> {
	private static final String LIBRARY = "matrixfromnative";

	/*
	 * TODO Note that since the Eigen part is currently not in progress, lines
	 * in Android.mk to build it is currently commented out
	 */

	private native float[][] matrixComputationByNative(float[][] floatsA, float[][] floatsB);

	static {
		System.loadLibrary(LIBRARY);
	};

	@Override
	public String getName() {
		return "Native Matrix Multiplication";
	}

	@Override
	public float[][] execute(MatrixPair input) {
		return matrixComputationByNative(input.matrixA, input.matrixB);
	}
}
