package com.emiliano.androidTestTool.core.metrics.operation;

import java.util.HashMap;
import java.util.Map;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;

public class MemFree<Input, Output> implements OperationMetric<Input, Output> {

	private MemoryInfo memoryInfo;
	private ActivityManager activityManager;
	long availableMegs;

	public MemFree(Context context) {
		memoryInfo = new MemoryInfo();
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

	}
	
	public static final String METRIC_NAME="Free memory in MBs";

	@Override
	public Long calculate(Output element) {
		long averageAvailableMegs = (availableMegs + getMemFree()) / 2;
		return averageAvailableMegs;
	}

	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		availableMegs = getMemFree();
	}

	public long getMemFree() {
		activityManager.getMemoryInfo(memoryInfo);
		return memoryInfo.availMem / 1048576L;
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}
	
}
