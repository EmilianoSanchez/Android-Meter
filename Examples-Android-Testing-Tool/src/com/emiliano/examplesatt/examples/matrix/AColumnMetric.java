package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.metrics.Metric;

public class AColumnMetric implements Metric<MatrixPair> {

	@Override
	public String getName() {
		return "AColumnMetric";
	}

	@Override
	public Object calculate(MatrixPair element) {
		return element.matrixA[0].length;
	}

}