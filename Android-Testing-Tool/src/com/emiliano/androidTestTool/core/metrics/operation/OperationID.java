package com.emiliano.androidTestTool.core.metrics.operation;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class OperationID<Input,Output> implements OperationMetric<Input,Output>{

	public static final String METRIC_NAME="Operation name";
	
	private String operationId;
	
	@Override
	public String calculate(Output element) {
		return operationId;
	}

	@Override
	public void beforeOperation(Input input,
			Component<Input, Output> component) {
		operationId=component.getName()+" "+input.toString();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}
}