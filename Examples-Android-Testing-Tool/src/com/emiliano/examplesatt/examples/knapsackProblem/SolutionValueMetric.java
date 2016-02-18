package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class SolutionValueMetric implements OperationMetric<KnapsackInstance,KnapsackSolution>{

	@Override
	public Object calculate(KnapsackSolution element) {
		return element.getValue();
	}

	@Override
	public String getName() {
		return "Solution value";
	}

	@Override
	public void beforeOperation(KnapsackInstance input, Component<KnapsackInstance, KnapsackSolution> component) {
		// DO NOTHING
	}

}
