package com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics;

import com.emiliano.androidMeter.core.metrics.Metric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class ComplexityMetric implements Metric<MatrixPair, Integer> {

	@Override
	public String getName() {
		return "ComplexityMetric";
	}

	@Override
	public Integer calculate(MatrixPair element) {
		return element.matrixA[0].length * element.matrixA.length * element.matrixB[0].length;
	}

}