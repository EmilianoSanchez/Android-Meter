package com.emiliano.androidTestTool.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

import android.content.Context;

public class TestPlanImpl<Input, Output> implements TestPlan<Input, Output> {

	private long delayBetweenOperationsMillis = 0;

	private String name;

	private Context context;

	private List<Loader<Input>> inputs;

	private List<Component<Input, Output>> components;

	private List<Metric<TestPlan<Input, Output>, ?>> globalMetrics;

	private List<Metric<Input, ?>> inputMetrics;

	private List<Metric<Component<Input, Output>, ?>> componentMetrics;

	private List<OperationMetric<Input, Output, ?>> operationMetrics;

	public TestPlanImpl(String name, Context context) {
		this.name = name;
		this.context = context;
		this.inputs = new LinkedList<Loader<Input>>();
		this.components = new LinkedList<Component<Input, Output>>();
		this.globalMetrics = new LinkedList<Metric<TestPlan<Input, Output>, ?>>();
		this.inputMetrics = new LinkedList<Metric<Input, ?>>();
		this.componentMetrics = new LinkedList<Metric<Component<Input, Output>, ?>>();
		this.operationMetrics = new LinkedList<OperationMetric<Input, Output, ?>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInputs(List<Loader<Input>> inputs) {
		this.inputs = inputs;
	}

	public void addInputs(Loader<Input>... inputs) {
		this.inputs.addAll(Arrays.asList(inputs));
	}
	
	public void addInputs(Input... inputs) {
		for(int i=0;i<inputs.length;i++)
			this.inputs.add(new SimpleLoader<Input>(inputs[i]));
	}

	public void setComponents(List<Component<Input, Output>> components) {
		this.components = components;
	}

	public void addComponents(Component<Input, Output>... components) {
		this.components.addAll(Arrays.asList(components));
	}

	public List<Metric<TestPlan<Input, Output>, ?>> getGlobalMetrics() {
		return globalMetrics;
	}

	public void setGlobalMetrics(List<Metric<TestPlan<Input, Output>, ?>> globalMetrics) {
		this.globalMetrics = globalMetrics;
	}

	public void addGlobalMetrics(Metric<TestPlan<Input, Output>, ?>... metrics) {
		this.globalMetrics.addAll(Arrays.asList(metrics));
	}

	public List<Metric<Input, ?>> getInputMetrics() {
		return inputMetrics;
	}

	public void setInputMetrics(List<Metric<Input, ?>> inputMetrics) {
		this.inputMetrics = inputMetrics;
	}

	public void addInputMetrics(Metric<Input, ?>... metrics) {
		this.inputMetrics.addAll(Arrays.asList(metrics));
	}

	public List<Metric<Component<Input, Output>, ?>> getComponentMetrics() {
		return componentMetrics;
	}

	public void setComponentMetrics(List<Metric<Component<Input, Output>, ?>> componentMetrics) {
		this.componentMetrics = componentMetrics;
	}

	public void addComponentMetrics(Metric<Component<Input, Output>, ?>... metrics) {
		this.componentMetrics.addAll(Arrays.asList(metrics));
	}

	public List<OperationMetric<Input, Output, ?>> getOperationMetrics() {
		return operationMetrics;
	}

	public void setOperationMetrics(List<OperationMetric<Input, Output, ?>> operationMetrics) {
		this.operationMetrics = operationMetrics;
	}

	public void addOperationMetrics(OperationMetric<Input, Output, ?>... metrics) {
		this.operationMetrics.addAll(Arrays.asList(metrics));
	}

	public long getDelayBetweenOperationsMillis() {
		return delayBetweenOperationsMillis;
	}

	public void setDelayBetweenOperationsMillis(long delayBetweenOperationsMillis) {
		this.delayBetweenOperationsMillis = delayBetweenOperationsMillis;
	}

	public Context getAndroidContext() {
		return context;
	}

	public void setAndroidContext(Context context) {
		this.context = context;
	}

	public List<Loader<Input>> getInputs() {
		return inputs;
	}

	public List<Component<Input, Output>> getComponents() {
		return components;
	}

}
