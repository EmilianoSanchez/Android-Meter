package com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics;

import com.emiliano.androidMeter.core.metrics.Metric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class BColumnMetric implements Metric<MatrixPair, Integer> {

	@Override
	public String getName() {
		return "BColumnMetric";
	}

	@Override
	public Integer calculate(MatrixPair element) {
		return element.matrixB[0].length;
	}

}