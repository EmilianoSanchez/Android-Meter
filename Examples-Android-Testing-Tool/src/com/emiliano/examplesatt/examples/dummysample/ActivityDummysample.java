package com.emiliano.examplesatt.examples.dummysample;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.utils.TestToolActivity;

public class ActivityDummysample   extends TestToolActivity<Integer[], Integer>{

	@Override
	protected TestPlan<Integer[], Integer> getPlan() {
		return new TestPlanSample();
	}
	
	@Override
	protected String getFileName() {
		return "Dummy sample";
	}
}