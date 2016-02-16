package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.metrics.Metric;

public class BColumnMetric implements Metric<MatrixPair> {

	@Override
	public String getName() {
		return "BColumnMetric";
	}

	@Override
	public Object calculate(MatrixPair element) {
		return element.matrixB[0].length;
	}

}