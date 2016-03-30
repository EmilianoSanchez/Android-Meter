package com.emiliano.androidTestTool.core.metrics.dynamicContext;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;
import com.emiliano.androidTestTool.core.metrics.staticContext.MemSize;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;

public class MemUsage <Input, Output> implements OperationMetric<Input, Output> {

	private MemoryInfo mi;
	private ActivityManager activityManager;
	double percentAvail;
	long totalMem;

	public MemUsage(TestPlan<Input, Output> testPlan) {
		mi = new MemoryInfo();
		activityManager = (ActivityManager) testPlan.getAndroidContext().getSystemService(Context.ACTIVITY_SERVICE);
		totalMem=new MemSize().calculate(testPlan);
	}

	@Override
	public Double calculate(Output element) {
		activityManager.getMemoryInfo(mi);
		double newPercentAvail =  1.0 - ((mi.availMem/ 1048576L) / totalMem);
		return (newPercentAvail+ percentAvail)/2.0;
	}

	@Override
	public String getName() {
		return "Memory usage in %";
	}

	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		activityManager.getMemoryInfo(mi);
		percentAvail = 1.0 - ((mi.availMem/ 1048576L) / totalMem);
	}

}