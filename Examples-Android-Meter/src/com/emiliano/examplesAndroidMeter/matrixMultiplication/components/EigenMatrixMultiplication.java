package com.emiliano.examplesAndroidMeter.matrixMultiplication.components;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class EigenMatrixMultiplication implements Component<MatrixPair, float[][]> {
	private static final String MSG_TAG = "EigenMatrixComputation";
	private static final String LIBRARY = "matrixfromeigen";

	private native float[][] matrixComputationByEigen(float[][] floatsA, float[][] floatsB);

	static {
		System.loadLibrary(LIBRARY);
	};

	@Override
	public String getName() {
		return "Matrix Multiplication with Eigen";
	}

	@Override
	public float[][] execute(MatrixPair input) {
		return matrixComputationByEigen(input.matrixA, input.matrixB);
	}
}
