package com.emiliano.evalfd;

import java.util.List;

import com.emiliano.androidTestTool.utils.TestToolActivity;
import com.emiliano.evalfd.testPlan.FDDB_DataSetUtils;
import com.emiliano.evalfd.testPlan.TestPlanFaceDetection;

import android.os.Bundle;
import android.widget.Toast;

public class EvaluationActivity extends TestToolActivity<InputFaceDetection,FaceDetectionResult>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		List<InputFaceDetection> list=FDDB_DataSetUtils.getInput("/storage/emulated/0/FDDB-folds-01/FDDB-fold-01.txt");
//		Toast.makeText(this, ""+list.size(), Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected TestPlanFaceDetection getPlan() {
		return new TestPlanFaceDetection(this.getApplicationContext());
	}
}