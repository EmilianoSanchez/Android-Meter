package com.emiliano.examplesAndroidMeter.matrixMultiplication;

import com.emiliano.androidMeter.core.BenchmarkPlan;
import com.emiliano.androidMeter.utils.BenchmarkToolActivity;

public class ActivityMatrix extends BenchmarkToolActivity<MatrixPair, float[][]> {

	@Override
	protected BenchmarkPlan<MatrixPair, float[][]>[] getBenchmarkPlans() {
		return  new BenchmarkPlan[]{new MatrixRandomBenchmarkPlan(this)};
	}

}