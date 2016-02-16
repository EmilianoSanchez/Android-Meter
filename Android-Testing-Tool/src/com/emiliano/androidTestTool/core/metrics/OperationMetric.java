package com.emiliano.androidTestTool.core.metrics;

import com.emiliano.androidTestTool.core.components.Component;

public interface OperationMetric<Input,Output> extends Metric<Output>{
	public String getName();
	public abstract void beforeOperation(Input input,Component<Input,Output> component);
}
