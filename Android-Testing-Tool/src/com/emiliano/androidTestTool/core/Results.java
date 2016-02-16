package com.emiliano.androidTestTool.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Results {

	private TestPlan testPlan;
	private List<Map<String, Object>> testPlanResults;
	private List<Map<String, Object>> inputResults;
	private List<Map<String, Object>> componentResults;
	private List<Map<String, Object>> operationResults;

	public Results(TestPlan testPlan) {
		this.testPlan=testPlan;
		this.testPlanResults = new LinkedList<Map<String, Object>>();
		this.inputResults = new LinkedList<Map<String, Object>>();
		this.componentResults = new LinkedList<Map<String, Object>>();
		this.operationResults = new LinkedList<Map<String, Object>>();
	}

	public List<Map<String, Object>> getDesnormalizedResults() {
		
		List<Map<String, Object>> results = new LinkedList<Map<String, Object>>();
		Iterator<Map<String,Object>> operationResultsIterator=operationResults.iterator();
		for (Map<String, Object> testPlanResult : testPlanResults) {
			for (Map<String, Object> inputResult : inputResults) {
				for (Map<String, Object> componentResult : componentResults) {
					for (int executionId=0;executionId<this.testPlan.getExecutionsPerOperation();executionId++) {
						Map<String, Object> result = new TreeMap<String, Object>();
						result.putAll(testPlanResult);
						result.putAll(inputResult);
						result.putAll(componentResult);
						if(this.testPlan.getExecutionsPerOperation()>1)	
							result.put("Execution ID", executionId);
						result.putAll(operationResultsIterator.next());
						results.add(result);
					}
				}
			}
		}
		return results;
	}

	public List<Map<String, Object>> getTestPlanResults() {
		return testPlanResults;
	}

	public List<Map<String, Object>> getInputResults() {
		return inputResults;
	}

	public List<Map<String, Object>> getComponentResults() {
		return componentResults;
	}

	public List<Map<String, Object>> getOperationResults() {
		return operationResults;
	}

	public void addTestPlanResult(Map<String, Object> testPlanResult) {
		testPlanResults.add(testPlanResult);
	}

	public void addInputResult(int inputId, Map<String, Object> inputResult) {
		inputResults.add(inputResult);
	}

	public void addComponentResult(int componentId, Map<String, Object> componentResult) {
		componentResults.add(componentResult);
	}

	public void addOperationResult(int inputId, int componentId, int executionId, Map<String, Object> operationResult) {
		operationResults.add(operationResult);
	}

}
