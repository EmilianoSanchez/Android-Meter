package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.metrics.Metric;

public class WeightRatioMetric implements Metric<KnapsackInstance, Double> {

	@Override
	public String getName() {
		return "KnapsackWeightRatio";
	}

	@Override
	public Double calculate(KnapsackInstance element) {
		double totalWeight = 0.0;
		for (int i = 0; i < element.itemWeights.length; i++)
			totalWeight += element.itemWeights[i];
		return element.knapsackWeight / totalWeight;
	}

}
