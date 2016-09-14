package com.emiliano.androidMeter.core;

import java.util.List;
import java.util.Map;

import com.emiliano.androidMeter.core.components.Component;
import com.emiliano.androidMeter.core.metrics.Metric;
import com.emiliano.androidMeter.core.metrics.OperationMetric;

//import android.app.ProgressDialog;
//import android.content.Context;
import android.os.AsyncTask;

public class Executor<Input, Output> extends AsyncTask<BenchmarkPlan<Input, Output>, String, Results<Input, Output>[]> {

	private ExecutorListener listener;

	public Executor(ExecutorListener listener) {
		this.listener = listener;
	}

	public Executor() {
		this(null);
	}

	@Override
	protected Results<Input, Output>[] doInBackground(BenchmarkPlan<Input, Output>... params) {
		Results<Input, Output>[] results = new Results[params.length];
		for (int tp = 0; tp < params.length; tp++) {
			BenchmarkPlan<Input, Output> plan = params[tp];
			if (plan != null) {
				results[tp] = executeBenchmarkPlan(plan);
			}
		}
		return results;
	}

	protected Results<Input, Output> executeBenchmarkPlan(BenchmarkPlan<Input, Output> plan) {
		this.publishProgress("executing " + plan.getName());
		ResultsImpl<Input, Output> results = new ResultsImpl<Input, Output>(plan);

		for (Metric<BenchmarkPlan<Input, Output>, ?> globalMetric : plan.getGlobalMetrics()) {
			results.addGlobalMeasure(globalMetric.getName(), globalMetric.calculate(plan));
		}

		List<Loader<Input>> inputs = plan.getInputs();
		List<Component<Input, Output>> components = plan.getComponents();
		
		for (int c = 0; c < components.size(); c++) {
			for (Metric<Component<Input, Output>, ?> componentMetric : plan.getComponentMetrics()) {
				results.addComponentMeasure(c, componentMetric.getName(), componentMetric.calculate(components.get(c)));
			}
		}

		int currentOperation = 0;
		int totalOperations = inputs.size() * components.size();

		for (int i = 0; i < inputs.size(); i++) {
			Loader<Input> inputLoader= inputs.get(i);
			inputLoader.loadElement();
			Input input = inputLoader.getElement();

			for (Metric<Input, ?> inputMetric : plan.getInputMetrics()) {
				results.addInputMeasure(i, inputMetric.getName(), inputMetric.calculate(input));
			}
			
			for (int c = 0; c < components.size(); c++) {
				Component<Input, Output> component = components.get(c);

				currentOperation++;
				if(currentOperation%100==0)
					this.publishProgress("executing " + plan.getName() + ": operation " + currentOperation + " of "
						+ totalOperations);

				for (OperationMetric<Input, Output, ?> operationMetric : plan.getOperationMetrics()) {
					operationMetric.onBeforeOperation(input, component);
				}
				System.gc();
				Output output = component.execute(input);
				for (OperationMetric<Input, Output, ?> operationMetric : plan.getOperationMetrics()) {
					results.addOperationMeasure(i, c, operationMetric.getName(), operationMetric.calculate(output));
				}

				if (plan.getDelayBetweenOperationsMillis() > 0) {
					synchronized (this) {
						try {
							wait(plan.getDelayBetweenOperationsMillis());
						} catch (InterruptedException exeption) {
							exeption.printStackTrace();
						}
					}
				}

			}
			
			inputLoader.releaseElement();
		}
		return results;
	}

	@Override
	protected void onPreExecute() {
		if (listener != null)
			this.listener.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(String... progress) {
		if (listener != null)
			this.listener.onProgressUpdate(progress);
	}

	@Override
	protected void onPostExecute(Results<Input, Output>[] result) {
		if (listener != null)
			this.listener.onPostExecute(result);
	}

	public interface ExecutorListener {
		public void onPreExecute();

		public void onProgressUpdate(String... progress);

		public void onPostExecute(Results[] results);

	}

}
