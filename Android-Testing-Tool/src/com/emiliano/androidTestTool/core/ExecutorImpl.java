package com.emiliano.androidTestTool.core;

import java.util.Map;

import com.emiliano.androidTestTool.core.components.Component;

public class ExecutorImpl<Input, Output> extends Executor<Input, Output>{
	
	public ExecutorImpl(ExecutorListener listener) {
		super(listener);
	}

	protected Results executeTestPlan(TestPlan<Input, Output> testPlan){
		this.publishProgress("executing "+testPlan.getName());
		Results results=new Results(testPlan);
		
		Map<String, Object> testPlanResults = testPlan.getGlobalMetrics().calculate(testPlan);
		results.addGlobalMeasure(testPlanResults);

		int currentOperation = 0;
		int totalOperations = testPlan.getInputs().size()
				* testPlan.getComponents().size();

		int componentId=-1;
		int inputId=-1;
		
		for (Input input : testPlan.getInputs()) {
			inputId++;
			
			Map<String, Object> inputResults = testPlan.getInputMetrics().calculate(input);
			results.addInputMeasure(inputId,inputResults);
			
			for (Component<Input, Output> component : testPlan.getComponents()) {
				componentId++;
				
				if(inputId==0){
					Map<String, Object> componentResults = testPlan.getComponentMetrics().calculate(component);
					results.addComponentMeasure(componentId,componentResults);
				}
				
				currentOperation++;
				this.publishProgress("executing "+testPlan.getName()+": operation "
						+ currentOperation + " of " + totalOperations);
				
				for(int executionId=0;executionId<testPlan.getExecutionsPerOperation();executionId++){
					testPlan.getOperationMetrics().beforeOperation(input, component);

					Output output = component.execute(input);

					Map<String, Object> operationResults = testPlan.getOperationMetrics().calculate(output);

					results.addOperationMeasure(inputId,componentId,executionId,operationResults);
					
					if(testPlan.getDelayBetweenOperations()>0){
						synchronized (this) {
							try {
								wait(testPlan.getDelayBetweenOperations());
							} catch (InterruptedException exeption) {
								exeption.printStackTrace();
							}
						}
					}
				}
			}
		}
		return results;
	}
}
