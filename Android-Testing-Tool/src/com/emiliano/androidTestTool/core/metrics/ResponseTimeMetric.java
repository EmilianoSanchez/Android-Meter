package com.emiliano.androidTestTool.core.metrics;

import java.util.Map;

import com.emiliano.androidTestTool.core.components.Component;

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
	public Object calculate(Output element) {
		return (double)(System.nanoTime()-initialNanoTime)/multiplier;
	}

	@Override
	public String getName() {
		return "Response time (in "+timeUnit.name()+")";
	}

	@Override
	public void beforeOperation(Input input, Component<Input, Output> component) {
		this.initialNanoTime=System.nanoTime();
	}

}
