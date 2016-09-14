package com.emiliano.examplesAndroidMeter.knapsackProblem.benchmarkPlans;

import com.emiliano.androidMeter.core.BenchmarkPlanImpl;
import com.emiliano.androidMeter.core.metrics.operation.ResponseTimeMetric;
import com.emiliano.examplesAndroidMeter.knapsackProblem.GreedyAlgorithm;
import com.emiliano.examplesAndroidMeter.knapsackProblem.KnapsackInstance;
import com.emiliano.examplesAndroidMeter.knapsackProblem.KnapsackInstanceLoader;
import com.emiliano.examplesAndroidMeter.knapsackProblem.KnapsackSolution;
import com.emiliano.examplesAndroidMeter.knapsackProblem.NumItemsMetric;
import com.emiliano.examplesAndroidMeter.knapsackProblem.WeightRatioMetric;

import android.content.Context;

/**
 * The Class GreedyResponseTimeBenchmarkPlan captures the response time of the Greedy algorithm for the Knapsack problem on bigger instances.
 */
public class GreedyResponseTimeBenchmarkPlan extends BenchmarkPlanImpl<KnapsackInstance, KnapsackSolution> {

	public GreedyResponseTimeBenchmarkPlan(Context context) {
		super("GreedyResponseTimeBenchmarkPlan",context);
		this.addComponents(new GreedyAlgorithm());

		for (int i = 5; i <= 200; i++) {
			for (int j = 1; j <= 24; j++) {
				this.addInputs(new KnapsackInstanceLoader(i, ((double)j)/25.0));
			}
		}

		// Input metrics
		this.addInputMetrics(new NumItemsMetric());
		this.addInputMetrics(new WeightRatioMetric());

		// Operation metrics
		this.addOperationMetrics(new ResponseTimeMetric());
	}
}