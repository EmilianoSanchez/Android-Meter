package com.emiliano.examplesatt.examples.dummysample;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.utils.TestToolActivity;

public class ActivityDummysample   extends TestToolActivity<Integer[], Integer>{

	@Override
	protected TestPlan<Integer[], Integer> getTestPlan() {
		return new TestPlanDummySample(this);
	}

}