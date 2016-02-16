package com.emiliano.androidTestTool.core;

public interface ExecutorListener {
	public void onTestPlanStarted();
	public void onProgressUpdate(String progress);
	public void onTestPlanFinished(Results[] results);
}
