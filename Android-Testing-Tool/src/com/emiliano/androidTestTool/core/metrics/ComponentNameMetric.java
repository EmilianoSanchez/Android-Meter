package com.emiliano.androidTestTool.core.metrics;

import com.emiliano.androidTestTool.core.components.Component;

public class ComponentNameMetric<Input,Output> implements Metric<Component<Input,Output>>{

	@Override
	public String getName() {
		return "Component name";
	}

	@Override
	public Object calculate(Component<Input,Output> element) {
		return element.getName();
	}

}