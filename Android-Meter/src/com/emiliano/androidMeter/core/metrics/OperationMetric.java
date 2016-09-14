package com.emiliano.androidMeter.core.metrics;

import com.emiliano.androidMeter.core.components.Component;

public interface OperationMetric<Input, Output, Measure> extends Metric<Output, Measure> {

	void onBeforeOperation(Input input, Component<Input, Output> component);
	
}
