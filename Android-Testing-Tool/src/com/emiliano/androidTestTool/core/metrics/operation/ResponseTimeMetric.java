package com.emiliano.androidTestTool.core.metrics.operation;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;

public class ResponseTimeMetric<Input,Output> implements OperationMetric<Input,Output>{

	private TimeUnit timeUnit;
	private long initialNanoTime;
	private double multiplier;
	
	public static enum TimeUnit{SECONDS,MILLISECONDS,MICROSECONDS,NANOSECONDS};
	
	public ResponseTimeMetric() {
		this(TimeUnit.SECONDS);
	}
	public ResponseTimeMetric(TimeUnit timeUnit) {
		this.timeUnit=timeUnit;
		switch(timeUnit){
		case SECONDS:
			this.multiplier=1000000000.0;
			break;
		case MILLISECONDS:
			this.multiplier=1000000.0;
			break;
		case MICROSECONDS:
			this.multiplier=1000.0;
			break;
		case NANOSECONDS:
			this.multiplier=1.0;
			break;
		}
	}
	
	@Override
	public Double calculate(Output element) {
		double rTime= (double)(System.nanoTime()-initialNanoTime)/multiplier;
		return rTime;
	}
	
	public static final String METRIC_NAME="Response time";
	
	private static final String[] METRIC_NAMES=new String[]{"Response time (in seconds)","Response time (in milliseconds)","Response time (in microseconds)",
			"Response time (in nanoseconds)"};

	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		this.initialNanoTime=System.nanoTime();
	}
	
	public void beforeOperation() {
		this.beforeOperation(null,null);
	}
	
	@Override
	public String getName() {
		return METRIC_NAMES[timeUnit.ordinal()];
	}
}
