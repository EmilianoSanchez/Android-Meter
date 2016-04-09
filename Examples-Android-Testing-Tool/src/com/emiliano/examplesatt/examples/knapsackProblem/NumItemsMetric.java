package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.metrics.Metric;

public class NumItemsMetric implements Metric<KnapsackInstance, Integer> {

	@Override
	public String getName() {
		return "NumItemsMetric";
	}

	@Override
	public Integer calculate(KnapsackInstance element) {
		return element.itemWeights.length;
	}
}
