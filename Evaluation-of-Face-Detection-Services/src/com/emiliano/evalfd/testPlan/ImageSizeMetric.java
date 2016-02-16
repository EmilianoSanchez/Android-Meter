package com.emiliano.evalfd.testPlan;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.evalfd.InputFaceDetection;

public class ImageSizeMetric implements Metric<InputFaceDetection> {

	@Override
	public String getName() {
		return "ImageSizeMetric";
	}

	@Override
	public Object calculate(InputFaceDetection element) {
		return element.file.length();
	}

}
