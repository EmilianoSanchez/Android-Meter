package com.emiliano.examplesAndroidMeter.dummySample;

import com.emiliano.androidMeter.core.BenchmarkPlanImpl;
import com.emiliano.androidMeter.core.metrics.operation.ResponseTimeMetric;

import android.content.Context;

public class BenchmarkPlanDummySample extends BenchmarkPlanImpl<Integer[], Integer> {

	public BenchmarkPlanDummySample(Context context) {
		super("Benchmark plan dummy sample", context);
		this.addComponents(new AdditionWithRandomDelayComponent(0, 500));
		this.addComponents(new AdditionWithRandomDelayComponent(500, 1000));
		this.addComponents(new AdditionWithRandomDelayComponent(1000, 2000));

		this.addInputs(new Integer[] { 1, 2, 3 });
		this.addInputs(new Integer[] { 4, 5, 6 });
		this.addInputs(new Integer[] { 7, 8, 9 });

		// this.addOperationMetrics(new
		// MeasurerBenchmarkPlanName<Integer[],Integer>());
		this.addOperationMetrics(new ResponseTimeMetric());
		// this.addOperationMetrics(new MeasureOperationResult());
	}

}
