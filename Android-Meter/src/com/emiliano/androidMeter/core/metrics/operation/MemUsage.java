package com.emiliano.androidMeter.core.metrics.operation;

import java.util.HashMap;
import java.util.Map;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.androidMeter.core.metrics.OperationMetric;
import com.emiliano.androidMeter.core.metrics.global.StaticContextProperties;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;

public class MemUsage<Input, Output> implements OperationMetric<Input, Output, Double> {

	private MemoryInfo memoryInfo;
	private ActivityManager activityManager;
	double percentAvail;
	long totalMem;

	public MemUsage(Context context) {
		memoryInfo = new MemoryInfo();
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		totalMem = (Long) StaticContextProperties.getProperty(StaticContextProperties.PropertyName.RAMcapacity);
	}

	public static final String METRIC_NAME = "Memory usage in %";

	@Override
	public Double calculate(Output element) {
		double newPercentAvail = 1.0 - getMemUsage();
		double memUsage = (newPercentAvail + percentAvail) / 2.0;
		return memUsage;
	}

	@Override
	public void onBeforeOperation(Input input, Component<Input, Output> component) {
		percentAvail = getMemUsage();
	}

	private double getMemUsage() {
		activityManager.getMemoryInfo(memoryInfo);
		double percentAvail = 1.0 - ((memoryInfo.availMem / 1048576L) / totalMem);
		return percentAvail;
	}

	@Override
	public String getName() {
		return METRIC_NAME;
	}

}