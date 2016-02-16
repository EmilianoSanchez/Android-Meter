package com.emiliano.androidTestTool.core.metrics.dynamicContext;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;

public class MemFree<Input, Output> implements OperationMetric<Input, Output> {

	private MemoryInfo mi;
	private ActivityManager activityManager;
	long availableMegs;

	public MemFree(Context context) {
		mi = new MemoryInfo();
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

	}

	@Override
	public Integer calculate(Output element) {
		activityManager.getMemoryInfo(mi);
		long averageAvailableMegs = (availableMegs + (mi.availMem / 1048576L)) / 2;
		return (int) averageAvailableMegs;
	}

	@Override
	public String getName() {
		return "Free memory in MBs";
	}

	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		activityManager.getMemoryInfo(mi);
		availableMegs = mi.availMem / 1048576L;
	}

}
