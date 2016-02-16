package com.emiliano.evalfd.testPlan;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.evalfd.InputFaceDetection;

public class ImageFormatMetric implements Metric<InputFaceDetection> {

	@Override
	public String getName() {
		return "ImageFormatMetric";
	}

	@Override
	public Object calculate(InputFaceDetection element) {
		// TODO Auto-generated method stub
		int lastindex=element.file.getName().lastIndexOf('.');
		return element.file.getName().substring(lastindex+1);
	}

}
