package com.emiliano.examplesatt.examples.knapsackProblem;

public class KnapsackInstance {

	public int[] itemValues;
	public int[] itemWeights;
	public int knapsackWeight;

	public static KnapsackInstance generateRandom(int numItems, int maxItemValue, int maxItemWeight,
			double knapsackWeightRatio) {
		if (numItems < 2)
			throw new IllegalArgumentException("The number of items must be greater or equal than 2");
		if (maxItemValue < 1 || maxItemWeight < 1)
			throw new IllegalArgumentException("Max item value and weight must be greater or equal than 1");
		if (knapsackWeightRatio <= 0.0 || knapsackWeightRatio >= 1)
			throw new IllegalArgumentException("The Knapsack Weight Ratio must be in the range (0.0,1.0)");
		KnapsackInstance instance = new KnapsackInstance();
		instance.itemValues = new int[numItems];
		instance.itemWeights = new int[numItems];
		int totalWeight = 0;
		for (int i = 0; i < numItems; i++) {
			instance.itemValues[i] = (int) (Math.random() * (maxItemValue - 1)) + 1;
			instance.itemWeights[i] = (int) (Math.random() * (maxItemWeight - 1)) + 1;
			totalWeight += instance.itemWeights[i];
		}
		instance.knapsackWeight = (int) (((double) totalWeight) * knapsackWeightRatio);
		return instance;
	};

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("Knapsack weight: ");
		string.append(this.knapsackWeight);
		string.append('\n');
		for (int i = 0; i < this.itemWeights.length; i++)
			string.append("Item " + i + " Value: " + this.itemValues[i] + " Weight: " + this.itemWeights[i] + '\n');
		return string.toString();
	}
}
