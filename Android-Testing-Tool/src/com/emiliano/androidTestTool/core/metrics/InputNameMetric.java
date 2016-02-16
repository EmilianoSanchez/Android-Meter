package com.emiliano.androidTestTool.core.metrics;

public class InputNameMetric<Input> implements Metric<Input>{
	
	@Override
	public String getName() {
		return "Input name";
	}

	@Override
	public Object calculate(Input element) {
		return element.toString();
	}

}
