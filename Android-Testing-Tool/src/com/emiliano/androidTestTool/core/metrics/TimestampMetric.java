package com.emiliano.androidTestTool.core.metrics;

import com.emiliano.androidTestTool.core.components.Component;

public class TimestampMetric<Input,Output> implements OperationMetric<Input,Output>{

	private long timestamp;

	@Override
	public Object calculate(Output element) {
		return timestamp;
	}

	@Override
	public String getName() {
		return "Timestamp before operation";
	}

	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		timestamp=System.currentTimeMillis();
	}


}
