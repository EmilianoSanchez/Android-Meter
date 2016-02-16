package com.emiliano.examplesatt;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.utils.TestToolActivity;
import com.emiliano.examplesatt.examples.matrix.MatrixPair;
import com.emiliano.examplesatt.examples.matrix.TestPlanMatrix;


public class MainActivity extends TestToolActivity<MatrixPair,double[][]>{

	@Override
	protected TestPlan<MatrixPair,double[][]> getPlan() {
		return new TestPlanMatrix(this);
	}
}