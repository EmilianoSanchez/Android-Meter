package com.emiliano.androidTestTool.core.metrics.global;

import com.emiliano.androidTestTool.context.DeviceModel;
import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.Metric;

public class DeviceModelName<Input, Output> implements Metric<TestPlan<Input, Output>, String> {

	public static final String METRIC_NAME = "Device Model";

	@Override
	public String calculate(TestPlan<Input, Output> element) {
		return new DeviceModel().toString();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}
}
