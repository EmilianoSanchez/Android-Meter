package com.emiliano.androidTestTool.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.opencsv.CSVWriter;


public class Results {

	private TestPlan testPlan;
	private List<Map<String, Object>> globalMeasures;
	private List<Map<String, Object>> inputMeasures;
	private List<Map<String, Object>> componentMeasures;
	private List<Map<String, Object>> operationMeasures;

	public Results(TestPlan testPlan) {
		this.testPlan = testPlan;
		this.globalMeasures = new LinkedList<Map<String, Object>>();
		this.inputMeasures = new LinkedList<Map<String, Object>>();
		this.componentMeasures = new LinkedList<Map<String, Object>>();
		this.operationMeasures = new LinkedList<Map<String, Object>>();
	}

	public TestPlan getTestPlan() {
		return this.testPlan;
	}

	public List<Map<String, Object>> getGlobalMeasures() {
		return globalMeasures;
	}

	public List<Map<String, Object>> getInputMeasures() {
		return inputMeasures;
	}

	public List<Map<String, Object>> getComponentMeasures() {
		return componentMeasures;
	}

	public List<Map<String, Object>> getOperationMeasures() {
		return operationMeasures;
	}

	public void addGlobalMeasure(Map<String, Object> testPlanResult) {
		globalMeasures.add(testPlanResult);
	}

	public void addInputMeasure(int inputId, Map<String, Object> inputResult) {
		inputMeasures.add(inputResult);
	}

	public void addComponentMeasure(int componentId, Map<String, Object> componentResult) {
		componentMeasures.add(componentResult);
	}

	public void addOperationMeasure(int inputId, int componentId, int executionId,
			Map<String, Object> operationResult) {
		operationMeasures.add(operationResult);
	}

	// public void save(File file) throws IOException {
	// List<Map<String, Object>>
	// desnormalizedResults=this.getDesnormalizedResults();
	// FileOutputStream writer = null;
	// writer = new FileOutputStream(file);
	// if(desnormalizedResults.size()>0){
	// Map<String, Object> operationResult = desnormalizedResults.get(0);
	//
	// writer.write("OperationID".getBytes());
	// for (String header : operationResult.keySet()) {
	// writer.write(";".getBytes());
	// writer.write(header.getBytes());
	// }
	// writer.write("\n".getBytes());
	//
	// for (int i = 0; i < desnormalizedResults.size(); i++) {
	// Map<String, Object> oper = desnormalizedResults.get(i);
	// writer.write(Integer.toString(i).getBytes());
	// for (Object object : oper.values()) {
	// writer.write(";".getBytes());
	// writer.write(object.toString().getBytes());
	// }
	// writer.write("\n".getBytes());
	// }
	// }
	// writer.flush();
	// writer.close();
	// }

	public void saveToCSV(File file) throws IOException {
		List<Map<String, Object>> desnormalizedResults = this.getDesnormalizedResults();
		CSVWriter writer = null;
		writer = new CSVWriter(new FileWriter(file), ';');
		if (desnormalizedResults.size() > 0) {
			Map<String, Object> operationResult = desnormalizedResults.get(0);
			String[] headers = new String[operationResult.size()];
			headers[0] = "OperationID";
			int pos = 1;
			for (String header : operationResult.keySet()) {
				headers[pos] = header;
				pos++;
			}
			writer.writeNext(headers);

			for (int i = 0; i < desnormalizedResults.size(); i++) {
				Map<String, Object> oper = desnormalizedResults.get(i);
				String[] head = new String[headers.length];
				head[0] = Integer.toString(i);
				int po = 1;
				for (Object object : oper.values()) {
					head[po] = object.toString();
					po++;
				}
				writer.writeNext(head);
			}
		}
		writer.flush();
		writer.close();
	}

	public List<Map<String, Object>> getDesnormalizedResults() {

		List<Map<String, Object>> results = new LinkedList<Map<String, Object>>();
		Iterator<Map<String, Object>> operationResultsIterator = operationMeasures.iterator();
		for (Map<String, Object> testPlanResult : globalMeasures) {
			for (Map<String, Object> inputResult : inputMeasures) {
				for (Map<String, Object> componentResult : componentMeasures) {
					for (int executionId = 0; executionId < this.testPlan.getExecutionsPerOperation(); executionId++) {
						Map<String, Object> result = new TreeMap<String, Object>();
						result.putAll(testPlanResult);
						result.putAll(inputResult);
						result.putAll(componentResult);
						if (this.testPlan.getExecutionsPerOperation() > 1)
							result.put("Execution ID", executionId);
						result.putAll(operationResultsIterator.next());
						results.add(result);
					}
				}
			}
		}
		return results;
	}
}
