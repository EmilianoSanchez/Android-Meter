package com.emiliano.androidTestTool.core;

import java.util.Collection;
import java.util.List;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

import android.content.Context;

public interface TestPlan<Input, Output> {

	public String getName();
	
	public Context getAndroidContext();
	
	public List<Input> getInputs();

	public List<Component<Input, Output>> getComponents();

	public Collection<Metric<TestPlan<Input, Output>>> getGlobalMetrics();

	public Collection<Metric<Input>> getInputMetrics();

	public Collection<Metric<Component<Input, Output>>> getComponentMetrics();

	public Collection<OperationMetric<Input, Output>> getOperationMetrics();

	public long getDelayBetweenOperations();
	
}
