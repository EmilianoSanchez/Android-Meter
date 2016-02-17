package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class SolutionValueMetric implements OperationMetric<KnapsackInstance,KnapsackSolution>{

	@Override
	public Object calculate(KnapsackSolution element) {
		double totalValue=0.0;
		for(double value:element.problem.itemValues)
			totalValue+=value;
		return totalValue;
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
