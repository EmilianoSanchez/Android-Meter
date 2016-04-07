package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.TestPlanImpl;
import com.emiliano.androidTestTool.core.metrics.component.ComponentName;
import com.emiliano.androidTestTool.core.metrics.operation.ResponseTimeMetric;

import android.content.Context;

public class TestPlanKnapsackProblem extends TestPlanImpl<KnapsackInstance,KnapsackSolution>{
	
	@SuppressWarnings("unchecked")
	public TestPlanKnapsackProblem(Context context) {
		super("Test plan Knapsack problem",context);
		this.addComponents(new GreedyAlgorithm());
		this.addComponents(new BacktrackingAlgorithm());
	
		int numItems[]=new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		double weightRatios[]=new double[]{0.1,0.3,0.5,0.7,0.9};
		for (int i = 0; i < numItems.length; i++) {
			for (int j = 0; j < weightRatios.length; j++) {
				this.addInputs(KnapsackInstance.generateRandom(numItems[i], 1000, 1000, weightRatios[j]));
			}
		}
	
		// Static context metrics
		// this.addContextMetrics(new Cpu2dMark());
		// this.addContextMetrics(new Cpu3dMark());
		// this.addContextMetrics(new CpuArchitecture());
		// this.addContextMetrics(new CpuCores());
		// this.addContextMetrics(new CpuMark());
		// this.addContextMetrics(new DeviceModel());
		// this.addContextMetrics(new DiskMark());
		// this.addContextMetrics(new MemMark());
		// this.addContextMetrics(new MemSize());
	
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
