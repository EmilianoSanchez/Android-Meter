package com.emiliano.examplesatt.examples.knapsackProblem;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.emiliano.androidTestTool.core.components.Component;

public class GreedyAlgorithm implements Component<KnapsackInstance,KnapsackSolution>{

	@Override
	public String getName() {
		return "GreedyAlgorithm";
	}

	@Override
	public KnapsackSolution execute(KnapsackInstance input) {
		KnapsackSolution solution=new KnapsackSolution(input);
		Vector<Map.Entry<Integer,Double>> order=valueWeightOrder(input);
		int availableWeight=input.knapsackWeight;
		for(int i=order.size()-1;i>=0;i--){
			int itemIndex=order.get(i).getKey();
			if(availableWeight>input.itemWeights[itemIndex]){
				solution.selections[itemIndex]=true;
				availableWeight-=input.itemWeights[itemIndex];
			}
		}
		return solution;
	}
	
    public Vector<Map.Entry<Integer,Double>> valueWeightOrder(KnapsackInstance input) {
    	Vector<Map.Entry<Integer,Double>> order=new Vector<Map.Entry<Integer,Double>>(input.itemWeights.length);
    	
        for (int i = 0; i < input.itemWeights.length; i++) {
        	order.add(i,new AbstractMap.SimpleEntry<Integer, Double>(i,(double)input.itemValues[i] / (double)input.itemWeights[i]));
        }
        Collections.sort(order,new Comparator<Map.Entry<Integer,Double>>() {

			@Override
			public int compare(Entry<Integer, Double> lhs, Entry<Integer, Double> rhs) {
				if(lhs.getValue()<rhs.getValue())
					return -1;
				else
					if(lhs.getValue()>rhs.getValue())
						return 1;
					else
						return 0;
			}
        });
		return order;
    }

}
