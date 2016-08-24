package com.emiliano.androidTestTool.core.metrics;

import com.emiliano.androidTestTool.core.components.Component;

public interface OperationMetric<Input, Output, Measure> extends Metric<Output, Measure> {

	void onBeforeOperation(Input input, Component<Input, Output> component);
}
