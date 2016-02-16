package com.emiliano.androidTestTool.core.metrics;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MetricSet<Element> {
	
	protected List<Metric<Element>> metrics;
	
	public MetricSet(){
		this.metrics=new LinkedList<Metric<Element>>();
	}
	
	public void addMetric(Metric<Element> metric){
		this.metrics.add(metric);
	};
	
	public Map<String,Object> calculate(Element element){
		Map<String,Object> measures=new TreeMap<String, Object>();
		for(Metric<Element> metric:metrics){
			measures.put(metric.getName(), metric.calculate(element));
		}
		return measures;
	};
}
