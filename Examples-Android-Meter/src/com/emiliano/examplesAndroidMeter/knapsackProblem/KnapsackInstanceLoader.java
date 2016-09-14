package com.emiliano.examplesAndroidMeter.knapsackProblem;

import com.emiliano.androidMeter.core.Loader;

public class KnapsackInstanceLoader implements Loader<KnapsackInstance> {

	public static KnapsackInstance generateRandom(int numItems, double knapsackWeightRatio) {
		if (numItems < 2)
			throw new IllegalArgumentException("The number of items must be greater or equal than 2");
		if (knapsackWeightRatio < 0.0 || knapsackWeightRatio > 1)
			throw new IllegalArgumentException("The Knapsack Weight Ratio must be in the range [0.0,1.0]");
		KnapsackInstance instance = new KnapsackInstance();
		instance.itemValues = new double[numItems];
		instance.itemWeights = new double[numItems];
		double totalWeight = 0.0;
		for (int i = 0; i < numItems; i++) {
			instance.itemValues[i] = Math.random();
			instance.itemWeights[i] = Math.random();
			totalWeight += instance.itemWeights[i];
		}
		instance.knapsackWeight = totalWeight * knapsackWeightRatio;
		return instance;
	}

	int numItems;
	double knapsackWeightRatio;
	KnapsackInstance element;

	public KnapsackInstanceLoader(int numItems, double knapsackWeightRatio) {
		this.numItems = numItems;
		this.knapsackWeightRatio = knapsackWeightRatio;
	}

	@Override
	public void loadElement() {
		this.element = generateRandom(numItems, knapsackWeightRatio);
	}

	@Override
	public void releaseElement() {
		this.element = null;
	}

	@Override
	public boolean isLoaded() {
		return this.element != null;
	}

	@Override
	public KnapsackInstance getElement() {
		return element;
	};
}
