package com.emiliano.androidTestTool.core.metrics.global;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.Metric;

public class TestPlanName<Input, Output> implements Metric<TestPlan<Input, Output>, String> {

	public static final String METRIC_NAME = "Test plan name";

	@Override
	public String calculate(TestPlan<Input, Output> element) {
		return element.getName();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}

}