package com.emiliano.examplesAndroidMeter.knapsackProblem;

import com.emiliano.androidMeter.core.components.Component;

public class BacktrackingAlgorithm implements Component<KnapsackInstance, KnapsackSolution> {

	@Override
	public String getName() {
		return "Backtracking";
	}

	KnapsackInstance input;
	boolean[] currentSolution;
	boolean[] bestSolution;
	double bestSolutionValue;

	@Override
	public KnapsackSolution execute(KnapsackInstance input) {
		this.input = input;
		this.currentSolution = new boolean[input.itemWeights.length];
		this.bestSolution = new boolean[input.itemWeights.length];
		this.bestSolutionValue = 0.0;

		backtracking(0, 0.0);

		KnapsackSolution result = new KnapsackSolution(input);
		result.selections = bestSolution;
		return result;
	}

	private double calculateSolutionValue(KnapsackInstance input, boolean[] solution) {
		double value = 0.0;
		for (int i = 0; i < solution.length; i++)
			if (solution[i])
				value += input.itemValues[i];
		return value;
	};

	private void backtracking(int index, double acumWeight) {
		if (index < input.itemWeights.length) {
			backtracking(index + 1, acumWeight);
			if (acumWeight + input.itemWeights[index] <= input.knapsackWeight) {
				currentSolution[index] = true;
				backtracking(index + 1, acumWeight + input.itemWeights[index]);
				currentSolution[index] = false;
			}
		} else {
			double currentSolutionValue = calculateSolutionValue(input, currentSolution);
			if (currentSolutionValue > bestSolutionValue) {
				bestSolutionValue = currentSolutionValue;
				for (int i = 0; i < bestSolution.length; i++)
					bestSolution[i] = currentSolution[i];
			}
		}
	}
}
