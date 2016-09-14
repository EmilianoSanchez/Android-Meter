package com.emiliano.examplesAndroidMeter.knapsackProblem;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.androidMeter.core.metrics.OperationMetric;

public class SolutionValueMetric implements OperationMetric<KnapsackInstance, KnapsackSolution, Double> {

	@Override
	public Double calculate(KnapsackSolution element) {
		return element.getValue();
	}

	public static final String METRIC_NAME = "SolutionValue";

	@Override
	public String getName() {
		return METRIC_NAME;
	}

	@Override
	public void onBeforeOperation(KnapsackInstance input, Component<KnapsackInstance, KnapsackSolution> component) {
	}

}
