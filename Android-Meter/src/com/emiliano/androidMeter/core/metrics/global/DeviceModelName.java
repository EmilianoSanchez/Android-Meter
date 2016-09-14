package com.emiliano.androidMeter.core.metrics.global;

import com.emiliano.androidMeter.context.DeviceModel;
import com.emiliano.androidMeter.core.BenchmarkPlan;
import com.emiliano.androidMeter.core.metrics.Metric;

public class DeviceModelName<Input, Output> implements Metric<BenchmarkPlan<Input, Output>, String> {

	public static final String METRIC_NAME = "Device Model";

	@Override
	public String calculate(BenchmarkPlan<Input, Output> element) {
		return new DeviceModel().toString();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}
}
