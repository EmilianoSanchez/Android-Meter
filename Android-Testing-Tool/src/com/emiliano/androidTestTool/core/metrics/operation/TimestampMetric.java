package com.emiliano.androidTestTool.core.metrics.operation;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class TimestampMetric<Input,Output> implements OperationMetric<Input,Output>{

	private long timestamp;
	
	@Override
	public Long calculate(Output element) {
		return timestamp;
	}

	public static final String METRIC_NAME="Timestamp";
	
	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		timestamp=System.currentTimeMillis();
	}

	public void beforeOperation() {
		beforeOperation(null,null);
	}
	
	@Override
	public String getName() {
		return METRIC_NAME;
	}
}
