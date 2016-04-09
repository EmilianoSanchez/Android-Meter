package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.metrics.Metric;

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