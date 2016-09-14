package com.emiliano.examplesAndroidMeter.dummySample;

import com.emiliano.androidMeter.core.components.Component;

public class AdditionWithRandomDelayComponent implements Component<Integer[], Integer> {

	private long minMillis, maxMillis;

	public AdditionWithRandomDelayComponent(long minMillis, long maxMillis) {
		this.minMillis = minMillis;
		this.maxMillis = maxMillis;
	}

	@Override
	public Integer execute(Integer[] input) {
		long randomDelay = (long) (Math.random() * (maxMillis - minMillis)) + minMillis;
		synchronized (this) {
			try {
				this.wait(randomDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int result = 0;
		for (int value : input) {
			result += value;
		}
		return result;
	}

	@Override
	public String getName() {
		return "BlackBoxAdditionWithRandomDelay";
	}

}
