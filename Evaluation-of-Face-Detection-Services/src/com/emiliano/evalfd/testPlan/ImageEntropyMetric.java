package com.emiliano.evalfd.testPlan;

import java.io.File;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.evalfd.InputFaceDetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageEntropyMetric implements Metric<InputFaceDetection> {

	@Override
	public String getName() {
		return "ImageEntropyMetric";
	}

	@Override
	public Object calculate(InputFaceDetection element) {
		File image=element.file;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize=4;
		Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
		
		return null;
	}

}
