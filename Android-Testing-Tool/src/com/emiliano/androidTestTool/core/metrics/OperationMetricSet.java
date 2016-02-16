package com.emiliano.androidTestTool.core.metrics;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.emiliano.androidTestTool.core.components.Component;

public class OperationMetricSet<Input,Output> extends MetricSet<Output>{
	
	protected List<OperationMetric<Input,Output>> metrics;
	
	public OperationMetricSet(){
		this.metrics=new LinkedList<OperationMetric<Input,Output>>();
	}
	
	public void addMetric(OperationMetric<Input,Output> metric){
		this.metrics.add(metric);
	};
	
	public void beforeOperation(Input input,Component<Input,Output> component){
		for(OperationMetric<Input,Output> metric:metrics){
			metric.beforeOperation(input, component);
		}
	};
	
	public Map<String,Object> calculate(Output element){
		Map<String,Object> measures=new TreeMap<String, Object>();
		for(Metric<Output> metric:metrics){
			measures.put(metric.getName(), metric.calculate(element));
		}
		return measures;
	};
}
