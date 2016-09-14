package com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics;

import com.emiliano.androidMeter.core.metrics.Metric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.MatrixPair;

public class AColumnMetric implements Metric<MatrixPair, Integer> {

	@Override
	public String getName() {
		return "AColumnMetric";
	}

	@Override
	public Integer calculate(MatrixPair element) {
		return element.matrixA[0].length;
	}

}