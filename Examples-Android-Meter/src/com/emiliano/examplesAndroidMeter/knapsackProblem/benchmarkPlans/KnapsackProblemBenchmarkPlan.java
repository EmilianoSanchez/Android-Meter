package com.emiliano.examplesAndroidMeter.knapsackProblem.benchmarkPlans;

import com.emiliano.androidMeter.core.BenchmarkPlanImpl;
import com.emiliano.androidMeter.core.metrics.component.ComponentName;
import com.emiliano.androidMeter.core.metrics.operation.ResponseTimeMetric;
import com.emiliano.examplesAndroidMeter.knapsackProblem.BacktrackingAlgorithm;
import com.emiliano.examplesAndroidMeter.knapsackProblem.GreedyAlgorithm;
import com.emiliano.examplesAndroidMeter.knapsackProblem.KnapsackInstance;
import com.emiliano.examplesAndroidMeter.knapsackProblem.KnapsackInstanceLoader;
import com.emiliano.examplesAndroidMeter.knapsackProblem.KnapsackSolution;
import com.emiliano.examplesAndroidMeter.knapsackProblem.NumItemsMetric;
import com.emiliano.examplesAndroidMeter.knapsackProblem.SolutionValueMetric;
import com.emiliano.examplesAndroidMeter.knapsackProblem.WeightRatioMetric;

import android.content.Context;


/**
 * The Class KnapsackProblemBenchmarkPlan captures the response time and solution value of a Greedy algorithm and a Backtracking algorithm for the Knapsack problem.
 * 
 */
public class KnapsackProblemBenchmarkPlan extends BenchmarkPlanImpl<KnapsackInstance, KnapsackSolution> {

	public KnapsackProblemBenchmarkPlan(Context context) {
		super("KnapsackProblemBenchmarkPlan",context);
		this.addComponents(new GreedyAlgorithm());
		this.addComponents(new BacktrackingAlgorithm());

		for (int i = 5; i <= 24; i++) {
			for (int j = 1; j <= 18; j++) {
				this.addInputs(new KnapsackInstanceLoader(i, ((double)j)/19.0));
			}
		}

		// Component metrics
		this.addComponentMetrics(new ComponentName());

		// Input metrics
		this.addInputMetrics(new NumItemsMetric());
		this.addInputMetrics(new WeightRatioMetric());

		// Operation metrics
		this.addOperationMetrics(new SolutionValueMetric());
		this.addOperationMetrics(new ResponseTimeMetric());
	}
}
