package com.emiliano.androidTestTool.core.metrics.staticContext;


import com.emiliano.androidTestTool.context.DeviceModel;
import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.Metric;

public class DeviceModelMetric<Input, Output> implements Metric<TestPlan<Input, Output>> {

	@Override
	public String getName() {
		return "Device Model";
	}

	@Override
	public DeviceModel calculate(TestPlan<Input, Output> element) {
		return new DeviceModel();
	}
}
