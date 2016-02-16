package com.emiliano.evalfd.testPlan;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;
import com.emiliano.evalfd.FaceDetectionResult;
import com.emiliano.evalfd.InputFaceDetection;

public class ImageNumberOfFacesMetric implements
		Metric<InputFaceDetection> {
	
	@Override
	public Object calculate(InputFaceDetection element) {
		return element.numberOfFaces;
	}

	@Override
	public String getName() {
		return "ImageNumberOfFacesMetric";
	}

}
