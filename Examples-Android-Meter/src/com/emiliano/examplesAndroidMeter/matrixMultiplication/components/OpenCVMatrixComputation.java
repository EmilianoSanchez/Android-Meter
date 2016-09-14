package com.emiliano.examplesAndroidMeter.matrixMultiplication.components;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

import android.util.Log;

public class OpenCVMatrixComputation implements Component<MatrixPair, float[][]> {
	private static final String MSG_TAG = "OpenCVMatrixComputation";

	static {
		try {
			System.loadLibrary("opencv_java3");
			// if (!OpenCVLoader.initDebug()) {
			// System.loadLibrary("opencv_core");
			// System.loadLibrary("libopencv_info");
			// System.loadLibrary("libopencv_java");
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public float[][] matrixMultiplication(float[][] a, float[][] b) {
		// if (!super.isValidMatrix(a, b)) throw new Exception("The matrices are
		// invalid");

		int rows = a.length;
		int cols = a[0].length;

		Mat m1 = createMatrixFromFloats(a);
		Mat m2 = createMatrixFromFloats(b);
		Mat output = Mat.zeros(rows, cols, CvType.CV_32FC1);

		try {
			Core.gemm(m1, m2, 1, m1, 0, output, 0);
		} catch (Exception e) {
			Log.e(MSG_TAG, e.getMessage());
		}

		return getFloatsFromCV(output);
	}

	private float[][] getFloatsFromCV(Mat matrix) {
		int rows = matrix.rows();
		int cols = matrix.cols();
		float[][] result = new float[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = (float) matrix.get(i, j)[0];
			}
		}
		return result;
	}

	private Mat createMatrixFromFloats(float[][] origin) {
		int rows = origin.length;
		int cols = origin[0].length;

		Mat matrix = new Mat(rows, cols, CvType.CV_32FC1);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix.get(i, j)[0] = (double) origin[i][j];
			}
		}

		return matrix;
	}

	@Override
	public String getName() {
		return "Matrix Multiplication with OpenCV";
	}

	@Override
	public float[][] execute(MatrixPair input) {
		return this.matrixMultiplication(input.matrixA, input.matrixB);
	}
}
