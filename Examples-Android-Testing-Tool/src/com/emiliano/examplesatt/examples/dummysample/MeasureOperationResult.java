package com.emiliano.examplesatt.examples.dummysample;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class MeasureOperationResult implements OperationMetric<Integer[], Integer, Integer> {

	@Override
	public Integer calculate(Integer element) {
		return element;
	}

	@Override
	public String getName() {
		return "Operation result";
	}

	@Override
	public void onBeforeOperation(Integer[] input, Component<Integer[], Integer> component) {
	}

}
