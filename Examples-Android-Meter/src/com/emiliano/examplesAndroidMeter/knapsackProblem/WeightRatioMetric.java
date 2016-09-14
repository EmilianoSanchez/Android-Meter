package com.emiliano.examplesAndroidMeter.knapsackProblem;

import com.emiliano.androidMeter.core.metrics.Metric;

public class WeightRatioMetric implements Metric<KnapsackInstance, Double> {

	public static final String METRIC_NAME = "KnapsackWeightRatio";

	@Override
	public String getName() {
		return METRIC_NAME;
	}

	@Override
	public Double calculate(KnapsackInstance element) {
		double totalWeight = 0.0;
		for (int i = 0; i < element.itemWeights.length; i++)
			totalWeight += element.itemWeights[i];
		return element.knapsackWeight / totalWeight;
	}

}
