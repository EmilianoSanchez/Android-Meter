package com.emiliano.examplesatt.examples.knapsackProblem;

public class KnapsackSolution {
	public KnapsackInstance problem;
	public boolean[] selections;

	public KnapsackSolution(KnapsackInstance problem) {
		this.problem = problem;
		this.selections = new boolean[problem.itemWeights.length];
	}

	public int getValue() {
		int value = 0;
		for (int i = 0; i < selections.length; i++)
			if (selections[i])
				value += problem.itemValues[i];
		return value;
	}

	public int getWeight() {
		int weight = 0;
		for (int i = 0; i < selections.length; i++)
			if (selections[i])
				weight += problem.itemWeights[i];
		return weight;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < selections.length; i++)
			string.append(selections[i] + ",");
		return string.toString();
	}
}
