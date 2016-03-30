package com.emiliano.androidTestTool.core;

import java.util.Collection;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.MetricSet;
import com.emiliano.androidTestTool.core.metrics.OperationMetricSet;

import android.content.Context;

public interface TestPlan<Input, Output> {

	public String getName();

	public MetricSet<TestPlan<Input, Output>> getGlobalMetrics();

	public MetricSet<Input> getInputMetrics();

	public MetricSet<Component<Input, Output>> getComponentMetrics();

	public OperationMetricSet<Input, Output> getOperationMetrics();
	
	public int getExecutionsPerOperation();

	public long getDelayBetweenOperations();

	public Context getAndroidContext();
	
	public Collection<Input> getInputs();

	public Collection<Component<Input, Output>> getComponents();

}
