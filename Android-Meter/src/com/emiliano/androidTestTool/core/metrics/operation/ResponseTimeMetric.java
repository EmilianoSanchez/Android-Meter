package com.emiliano.androidTestTool.core.metrics.operation;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class ResponseTimeMetric<Input, Output> implements OperationMetric<Input, Output, Double> {

	private TimeUnit timeUnit;
	private long initialNanoTime;
	private double multiplier;
	private String name;

	public static enum TimeUnit {
		SECONDS, MILLISECONDS, MICROSECONDS, NANOSECONDS
	}

	public ResponseTimeMetric() {
		this(TimeUnit.SECONDS);
	}

	public ResponseTimeMetric(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
		switch (timeUnit) {
		case SECONDS:
			this.multiplier = 1000000000.0;
			break;
		case MILLISECONDS:
			this.multiplier = 1000000.0;
			break;
		case MICROSECONDS:
			this.multiplier = 1000.0;
			break;
		case NANOSECONDS:
			this.multiplier = 1.0;
			break;
		}
		this.name = METRIC_NAME + " (in " + this.timeUnit.name() + ")";
	}

	@Override
	public Double calculate(Output element) {
		double rTime = (double) (System.nanoTime() - initialNanoTime) / multiplier;
		return rTime;
	}

	public static final String METRIC_NAME = "Response time";

	@Override
	public void onBeforeOperation(Input input, Component<Input, Output> component) {
		this.initialNanoTime = System.nanoTime();
	}

	public void onBeforeOperation() {
		this.onBeforeOperation(null, null);
	}

	@Override
	public String getName() {
		return name;
	}

	public static <Input, Output> ResponseTimeMetric<Input, Output> inSeconds() {
		return new ResponseTimeMetric<Input, Output>(TimeUnit.SECONDS);
	}
}
