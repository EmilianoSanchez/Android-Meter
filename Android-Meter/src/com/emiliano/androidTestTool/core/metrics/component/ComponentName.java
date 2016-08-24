package com.emiliano.androidTestTool.core.metrics.component;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.Metric;

public class ComponentName<Input, Output> implements Metric<Component<Input, Output>, String> {

	public static final String METRIC_NAME = "Component name";

	@Override
	public String calculate(Component<Input, Output> element) {
		return element.getName();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}

}