package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.utils.TestToolActivity;

public class ActivityKnapsackProblem  extends TestToolActivity<KnapsackInstance,KnapsackSolution>{

	@Override
	protected TestPlan<KnapsackInstance,KnapsackSolution> getPlan() {
		return new TestPlanKnapsackProblem(this);
	}
	
	@Override
	protected String getFileName() {
		return "Knapsack Problem";
	}
}