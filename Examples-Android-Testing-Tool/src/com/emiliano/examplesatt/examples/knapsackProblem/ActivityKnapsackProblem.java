package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.utils.TestToolActivity;

public class ActivityKnapsackProblem extends TestToolActivity<KnapsackInstance, KnapsackSolution> {

	@Override
	protected TestPlan<KnapsackInstance, KnapsackSolution> getTestPlan() {
		return new TestPlanKnapsackProblem(this);
	}

}