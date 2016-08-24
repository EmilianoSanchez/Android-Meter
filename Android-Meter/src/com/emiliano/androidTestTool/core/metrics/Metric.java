package com.emiliano.androidTestTool.core.metrics;

import java.util.Map;

public interface Metric<Element, Measure> {

	String getName();

	Measure calculate(Element element);
}
