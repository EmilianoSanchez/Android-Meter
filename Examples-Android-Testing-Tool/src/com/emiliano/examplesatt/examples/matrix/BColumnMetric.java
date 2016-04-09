package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.metrics.Metric;

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