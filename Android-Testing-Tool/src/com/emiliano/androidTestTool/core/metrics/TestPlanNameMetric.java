package com.emiliano.androidTestTool.core.metrics;

import com.emiliano.androidTestTool.core.TestPlan;

public class TestPlanNameMetric<Input,Output> implements Metric<TestPlan<Input,Output>>{

	@Override
	public String getName() {
		return "Test plan name";
	}

	@Override
	public Object calculate(TestPlan<Input,Output> element) {
		return element.getName();
	}

}
