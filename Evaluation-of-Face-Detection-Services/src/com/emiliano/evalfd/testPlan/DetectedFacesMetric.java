package com.emiliano.evalfd.testPlan;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;
import com.emiliano.evalfd.FaceDetectionResult;
import com.emiliano.evalfd.InputFaceDetection;

public class DetectedFacesMetric implements
		OperationMetric<InputFaceDetection, FaceDetectionResult> {

	@Override
	public Object calculate(FaceDetectionResult element) {
		if(element!=null && element.getDetectedFaces()!=null)
			return element.getDetectedFaces().length;
		else
			return -1;
	}

	@Override
	public String getName() {
		return "Detected Faces";
	}

	@Override
	public void beforeOperation(InputFaceDetection input,
			Component<InputFaceDetection, FaceDetectionResult> component) {
	}

}
