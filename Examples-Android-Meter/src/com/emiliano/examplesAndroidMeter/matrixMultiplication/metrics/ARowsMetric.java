package com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics;

import com.emiliano.androidMeter.core.metrics.Metric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class ARowsMetric implements Metric<MatrixPair, Integer> {

	@Override
	public String getName() {
		return "ARowsMetric";
	}

	@Override
	public Integer calculate(MatrixPair element) {
		return element.matrixA.length;
	}

}
