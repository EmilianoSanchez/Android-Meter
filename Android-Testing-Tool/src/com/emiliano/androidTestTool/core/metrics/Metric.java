package com.emiliano.androidTestTool.core.metrics;

public interface Metric<Element> {
	
	public String getName();
	
	public abstract Object calculate(Element element);
}
