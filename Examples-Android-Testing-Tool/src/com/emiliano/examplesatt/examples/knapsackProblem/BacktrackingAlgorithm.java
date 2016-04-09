package com.emiliano.examplesatt.examples.knapsackProblem;

import com.emiliano.androidTestTool.core.components.Component;

public class BacktrackingAlgorithm implements Component<KnapsackInstance, KnapsackSolution> {

	@Override
	public String getName() {
		return "DynamicProgramming";
	}

	@Override
	public KnapsackSolution execute(KnapsackInstance input) {
		KnapsackSolution solution = new KnapsackSolution(input);
		boolean[] bestSolution = new boolean[input.itemWeights.length];
		backtracking(input, bestSolution, new int[] { 0 }, solution.selections, 0, 0);
		for (int i = 0; i < bestSolution.length; i++)
			solution.selections[i] = bestSolution[i];
		return solution;
	}

	private static int calculateSolutionValue(KnapsackInstance input, boolean[] solution) {
		int value = 0;
		for (int i = 0; i < solution.length; i++)
			if (solution[i])
				value += input.itemValues[i];
		return value;
	};

	private static void backtracking(KnapsackInstance input, boolean[] bestSolution, int[] bestSolutionValue,
			boolean[] currentSolution, int index, int acumWeight) {
		if (index < input.itemWeights.length) {
			if (acumWeight <= input.knapsackWeight) {
				backtracking(input, bestSolution, bestSolutionValue, currentSolution, index + 1, acumWeight);
				currentSolution[index] = true;
				backtracking(input, bestSolution, bestSolutionValue, currentSolution, index + 1,
						acumWeight + input.itemWeights[index]);
				currentSolution[index] = false;
			}
		} else {
			int currentSolutionValue = calculateSolutionValue(input, currentSolution);
			if (currentSolutionValue > bestSolutionValue[0]) {
				bestSolutionValue[0] = currentSolutionValue;
				for (int i = 0; i < bestSolution.length; i++)
					bestSolution[i] = currentSolution[i];
			}
		}
	}
}
