package com.emiliano.examplesAndroidMeter.knapsackProblem;

import com.emiliano.androidMeter.core.BenchmarkPlan;
import com.emiliano.androidMeter.utils.BenchmarkToolActivity;
import com.emiliano.examplesAndroidMeter.knapsackProblem.benchmarkPlans.KnapsackProblemBenchmarkPlan;

public class ActivityKnapsackProblem extends BenchmarkToolActivity<KnapsackInstance, KnapsackSolution> {

	@Override
	protected BenchmarkPlan<KnapsackInstance, KnapsackSolution>[] getBenchmarkPlans() {
		return new BenchmarkPlan[] { new KnapsackProblemBenchmarkPlan(this) };
	}

}