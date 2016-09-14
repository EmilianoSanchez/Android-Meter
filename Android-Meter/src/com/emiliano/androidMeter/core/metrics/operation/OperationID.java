package com.emiliano.androidMeter.core.metrics.operation;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.androidMeter.core.metrics.OperationMetric;

public class OperationID<Input, Output> implements OperationMetric<Input, Output, String> {

	public static final String METRIC_NAME = "Operation ID";

	private String operationId;

	@Override
	public String calculate(Output element) {
		return operationId;
	}

	@Override
	public void onBeforeOperation(Input input, Component<Input, Output> component) {
		operationId = component.getName() + " " + input.toString();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}
}