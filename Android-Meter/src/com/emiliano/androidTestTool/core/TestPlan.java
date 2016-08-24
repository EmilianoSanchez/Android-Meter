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

	public List<Loader<Input>> getInputs();

	public List<Component<Input, Output>> getComponents();

	public List<Metric<TestPlan<Input, Output>, ?>> getGlobalMetrics();

	public List<Metric<Input, ?>> getInputMetrics();

	public List<Metric<Component<Input, Output>, ?>> getComponentMetrics();

	public List<OperationMetric<Input, Output, ?>> getOperationMetrics();

	public long getDelayBetweenOperationsMillis();

}
