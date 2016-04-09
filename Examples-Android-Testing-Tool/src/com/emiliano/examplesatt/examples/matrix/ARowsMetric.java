package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.metrics.Metric;

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
