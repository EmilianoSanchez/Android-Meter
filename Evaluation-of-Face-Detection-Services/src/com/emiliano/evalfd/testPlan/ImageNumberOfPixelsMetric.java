package com.emiliano.evalfd.testPlan;

import java.io.File;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.evalfd.InputFaceDetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageNumberOfPixelsMetric implements Metric<InputFaceDetection> {

	@Override
	public String getName() {
		return "ImageNumberOfPixelsMetric";
	}

	@Override
	public Object calculate(InputFaceDetection element) {
		File image=element.file;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
		return bmOptions.outHeight*bmOptions.outWidth;
	}

}
