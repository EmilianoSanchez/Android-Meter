package com.emiliano.androidTestTool.core;

//import android.app.ProgressDialog;
//import android.content.Context;
import android.os.AsyncTask;

public abstract class Executor<Input, Output> extends
		AsyncTask<TestPlan<Input, Output>, String, Results[]> {

	private ExecutorListener listener;

	public Executor(ExecutorListener listener) {
		this.listener = listener; 
	}
//	public Executor(Context context) {
//		this(new ExecutorProgressDialog(context));
//	}

	@Override
	protected Results[] doInBackground(TestPlan<Input, Output>... params) {
		Results[] results = new Results[params.length];
		for(int tp=0;tp<params.length;tp++){		
			TestPlan<Input, Output> testPlan = params[tp];
			if(testPlan!=null){
				results[tp]=executeTestPlan(testPlan);
			}
		}
		return results;
	}

	protected abstract Results executeTestPlan(TestPlan<Input, Output> testPlan);

	@Override
	protected void onPreExecute() {
		if(listener!=null)
			this.listener.onTestPlanStarted();
	}

	@Override
	protected void onProgressUpdate(String... progress) {
		if(listener!=null)
			this.listener.onProgressUpdate(progress[0]);
	}

	@Override
	protected void onPostExecute(Results[] result) {
		if(listener!=null)
			this.listener.onTestPlanFinished(result);
	}
	
	public interface ExecutorListener {
		public void onTestPlanStarted();
		public void onProgressUpdate(String progress);
		public void onTestPlanFinished(Results[] results);
	}
	
//	protected static class ExecutorProgressDialog extends ProgressDialog implements ExecutorListener{
//
//		public ExecutorProgressDialog(Context context) {
//			super(context);
//		}
//
//		@Override
//		public void onTestPlanStarted() {
//			this.setMessage("Starting test plan");
//			this.show();
//		}
//
//		@Override
//		public void onProgressUpdate(String progress) {
//			this.setMessage(progress);
//		}
//
//		@Override
//		public void onTestPlanFinished(Results[] results) {
//			this.dismiss();
//		}
//		
//	}
}
