package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.metrics.Metric;

public class NumItemsMetric implements Metric<KnapsackInstance>{
	
	@Override
	public String getName() {
		return "NumItemsMetric";
	}

	@Override
	public Object calculate(KnapsackInstance element) {
		return element.itemWeights.length;
	}
}
