package com.emiliano.androidTestTool.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.MetricSet;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;
import com.emiliano.androidTestTool.core.metrics.OperationMetricSet;

import android.content.Context;

public class TestPlan<Input, Output> {

	private int executionsPerOperation = 1;

	private long delayBetweenOperations = 0;
	
	private String name = "Test Plan";
	
	private Context context;

	private Collection<Input> inputs;

	private Collection<Component<Input, Output>> components;
	
	private MetricSet<TestPlan<Input, Output>> testPlanMetrics;

	private MetricSet<Input> inputMetrics;

	private MetricSet<Component<Input, Output>> componentMetrics;

	private OperationMetricSet<Input, Output> operationMetrics;

	public TestPlan() {
		this.inputs = new LinkedList<Input>();
		this.components = new LinkedList<Component<Input, Output>>();
		this.testPlanMetrics = new MetricSet<TestPlan<Input, Output>>();
		this.inputMetrics = new MetricSet<Input>();
		this.componentMetrics = new MetricSet<Component<Input, Output>>();
		this.operationMetrics = new OperationMetricSet<Input, Output>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInputs(Collection<Input> inputs) {
		this.inputs = inputs;
	}

	public void addInputs(Input... inputs) {
		this.inputs.addAll(Arrays.asList(inputs));
	}

	public void setComponents(Collection<Component<Input, Output>> components) {
		this.components = components;
	}

	public void addComponents(Component<Input, Output>... components) {
		this.components.addAll(Arrays.asList(components));
	}

	public MetricSet<TestPlan<Input, Output>> getTestPlanMetrics() {
		return testPlanMetrics;
	}

	public void setTestPlanMetrics(MetricSet<TestPlan<Input, Output>> testPlanMetrics) {
		this.testPlanMetrics = testPlanMetrics;
	}

	public void addTestPlanMetrics(Metric<TestPlan<Input, Output>> metric) {
		this.testPlanMetrics.addMetric(metric);
	}

	public MetricSet<Input> getInputMetrics() {
		return inputMetrics;
	}

	public void setInputMetrics(MetricSet<Input> inputMetrics) {
		this.inputMetrics = inputMetrics;
	}

	public void addInputMetrics(Metric<Input> metric) {
		this.inputMetrics.addMetric(metric);
	}

	public MetricSet<Component<Input, Output>> getComponentMetrics() {
		return componentMetrics;
	}

	public void setComponentMetrics(MetricSet<Component<Input, Output>> componentMetrics) {
		this.componentMetrics = componentMetrics;
	}

	public void addComponentMetrics(Metric<Component<Input, Output>> metrics) {
		this.componentMetrics.addMetric(metrics);
	}

	public OperationMetricSet<Input, Output> getOperationMetrics() {
		return operationMetrics;
	}

	public void setOperationMetrics(OperationMetricSet<Input, Output> operationMetrics) {
		this.operationMetrics = operationMetrics;
	}

	public void addOperationMetrics(OperationMetric<Input, Output> metric) {
		this.operationMetrics.addMetric(metric);
	}
	
	public int getExecutionsPerOperation() {
		return executionsPerOperation;
	}

	public void setExecutionsPerOperation(int executionsPerOperation) {
		this.executionsPerOperation = executionsPerOperation;
	}

	public long getDelayBetweenOperations() {
		return delayBetweenOperations;
	}

	public void setDelayBetweenOperations(long delayBetweenOperations) {
		this.delayBetweenOperations = delayBetweenOperations;
	}

	public Context getAndroidContext() {
		return context;
	}
	
	public void setAndroidContext(Context context) {
		this.context=context;
	}

	public Collection<Input> getInputs() {
		return inputs;
	}

	public Collection<Component<Input, Output>> getComponents() {
		return components;
	}

}
