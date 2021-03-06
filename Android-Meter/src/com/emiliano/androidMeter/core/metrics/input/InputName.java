package com.emiliano.androidMeter.core.metrics.input;

import com.emiliano.androidMeter.core.metrics.Metric;

public class InputName<Input> implements Metric<Input, String> {

	public static final String METRIC_NAME = "Input name";

	@Override
	public String calculate(Input element) {
		return element.toString();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}

}
