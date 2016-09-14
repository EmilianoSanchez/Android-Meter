package com.emiliano.androidMeter.core.metrics;

public interface Metric<Element, Measure> {

	String getName();

	Measure calculate(Element element);
}
