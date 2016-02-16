package com.emiliano.examplesatt.examples.dummysample;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.ResponseTimeMetric;

public class TestPlanSample extends TestPlan<Integer[], Integer> {
	@SuppressWarnings("unchecked")
	public TestPlanSample() {
		this.addComponents(new AdditionWithRandomDelayComponent(0, 500));
		this.addComponents(new AdditionWithRandomDelayComponent(500, 1000));
		this.addComponents(new AdditionWithRandomDelayComponent(1000, 2000));

		this.addInputs(new Integer[] { 1, 2, 3 });
		this.addInputs(new Integer[] { 4, 5, 6 });
		this.addInputs(new Integer[] { 7, 8, 9 });

		// this.addOperationMetrics(new
		// MeasurerTestPlanName<Integer[],Integer>());
		this.addOperationMetrics(new ResponseTimeMetric<Integer[], Integer>());
		// this.addOperationMetrics(new MeasureOperationResult());
	}

}
