package com.emiliano.androidMeter.core.metrics.global;

import com.emiliano.androidMeter.core.BenchmarkPlan;
import com.emiliano.androidMeter.core.metrics.Metric;

public class BenchmarkPlanName<Input, Output> implements Metric<BenchmarkPlan<Input, Output>, String> {

	public static final String METRIC_NAME = "Benchmark plan name";

	@Override
	public String calculate(BenchmarkPlan<Input, Output> element) {
		return element.getName();
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}

}