package com.emiliano.examplesAndroidMeter.dummySample;

import com.emiliano.androidMeter.core.BenchmarkPlan;
import com.emiliano.androidMeter.utils.BenchmarkToolActivity;

public class ActivityDummysample extends BenchmarkToolActivity<Integer[], Integer> {

	@Override
	protected BenchmarkPlan<Integer[], Integer>[] getBenchmarkPlans() {
		return new BenchmarkPlan[]{new BenchmarkPlanDummySample(this)};
	}

}