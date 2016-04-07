package com.emiliano.androidTestTool.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class ResultsImpl<Input,Output> implements Results<Input,Output>{

	private TestPlan<Input,Output> testPlan;
	private Map<String, Object> globalMeasures;
	private List<Map<String, Object>> inputMeasures;
	private List<Map<String, Object>> componentMeasures;
	private List<List<Map<String, Object>>> operationMeasures;

	public ResultsImpl(TestPlan<Input,Output> testPlan) {

		this.testPlan = testPlan;
		int numberOfInputs=testPlan.getInputs().size();
		int numberOfComponents=testPlan.getComponents().size();
		
		this.globalMeasures = new HashMap<String, Object>();
		
		this.inputMeasures = new ArrayList<Map<String, Object>>(numberOfInputs);
		for(int i=0;i<numberOfInputs;i++)
			this.inputMeasures.add(new HashMap<String, Object>());
		
		this.componentMeasures = new ArrayList<Map<String, Object>>(numberOfComponents);
		for(int c=0;c<numberOfComponents;c++)
			this.componentMeasures.add(new HashMap<String, Object>());
			
		this.operationMeasures = new ArrayList<List<Map<String, Object>>>(numberOfInputs);
		for(int i=0;i<numberOfInputs;i++){
			List<Map<String, Object>> aux=new ArrayList<Map<String, Object>>(numberOfComponents);
			for(int c=0;c<numberOfComponents;c++)
				aux.add(new HashMap<String, Object>());
			this.operationMeasures.add(aux);
		}
	}

	public TestPlan<Input,Output> getTestPlan() {
		return this.testPlan;
	}

	public Map<String, Object> getGlobalMeasures() {
		return this.globalMeasures;
	}

	public List<Map<String, Object>> getInputMeasures() {
		return this.inputMeasures;
	}
	
	public Map<String, Object> getInputMeasures(int inputIndex) {
		return this.inputMeasures.get(inputIndex);
	}

	public List<Map<String, Object>> getComponentMeasures() {
		return this.componentMeasures;
	}
	
	public Map<String, Object> getComponentMeasures(int componentIndex) {
		return this.componentMeasures.get(componentIndex);
	}

	public List<List<Map<String, Object>>> getOperationMeasures() {
		return this.operationMeasures;
	}
	
	public Map<String, Object> getOperationMeasures(int inputIndex,int componentIndex) {
		return this.operationMeasures.get(inputIndex).get(componentIndex);
	}

	public void addGlobalMeasures(Map<String, Object> globalMeasures) {
		this.globalMeasures.putAll(globalMeasures);
	}
	
	public void addGlobalMeasure(String name, Object value) {
		this.globalMeasures.put(name,value);
	}

	public void addInputMeasures(int inputIndex, Map<String, Object> inputMeasures) {
		this.inputMeasures.get(inputIndex).putAll(inputMeasures);
	}
	
	public void addInputMeasure(int inputIndex, String name, Object value) {
		this.inputMeasures.get(inputIndex).put(name,value);
	}

	public void addComponentMeasures(int componentIndex, Map<String, Object> componentMeasures) {
		this.componentMeasures.get(componentIndex).putAll(componentMeasures);
	}
	
	public void addComponentMeasure(int componentIndex, String name, Object value) {
		this.componentMeasures.get(componentIndex).put(name,value);
	}

	public void addOperationMeasures(int inputIndex, int componentIndex,
			Map<String, Object> operationMeasures) {
		this.operationMeasures.get(inputIndex).get(componentIndex).putAll(operationMeasures);
	}
	
	public void addOperationMeasure(int inputIndex, int componentIndex,
			String name, Object value) {
		this.operationMeasures.get(inputIndex).get(componentIndex).put(name,value);
	}
}
