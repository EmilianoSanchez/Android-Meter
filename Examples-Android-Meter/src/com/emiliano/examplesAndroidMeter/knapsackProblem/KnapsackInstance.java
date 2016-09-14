package com.emiliano.examplesAndroidMeter.knapsackProblem;

public class KnapsackInstance {

	public double[] itemValues;
	public double[] itemWeights;
	public double knapsackWeight;

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
