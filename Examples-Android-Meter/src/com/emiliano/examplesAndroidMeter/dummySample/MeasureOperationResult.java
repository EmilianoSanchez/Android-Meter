package com.emiliano.examplesAndroidMeter.dummySample;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.androidMeter.core.metrics.OperationMetric;

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
