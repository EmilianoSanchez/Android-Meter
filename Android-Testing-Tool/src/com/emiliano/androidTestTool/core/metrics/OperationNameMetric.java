package com.emiliano.androidTestTool.core.metrics;

import com.emiliano.androidTestTool.core.components.Component;

public class OperationNameMetric<Input,Output> implements OperationMetric<Input,Output>{

	private String operationName;
	
	@Override
	public Object calculate(Output element) {
		return operationName;
	}

	@Override
	public String getName() {
		return "Operation name";
	}

	@Override
	public void beforeOperation(Input input,
			Component<Input, Output> component) {
		operationName=component.getName()+" "+input.toString();
	}

}
