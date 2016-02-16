package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.metrics.Metric;

public class ARowsMetric implements Metric<MatrixPair> {

	@Override
	public String getName() {
		return "ARowsMetric";
	}

	@Override
	public Object calculate(MatrixPair element) {
		return element.matrixA.length;
	}

}
