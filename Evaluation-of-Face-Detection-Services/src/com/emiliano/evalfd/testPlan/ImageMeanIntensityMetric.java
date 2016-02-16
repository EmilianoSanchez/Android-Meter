package com.emiliano.evalfd.testPlan;

import java.io.File;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.evalfd.InputFaceDetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

//Mean intensity, value or brightness (they are quite the same. Only brightness has a slightly different meaning)
public class ImageMeanIntensityMetric implements Metric<InputFaceDetection> {

	@Override
	public String getName() {
		return "ImageMeanIntensityMetric";
	}

	@Override
	public Object calculate(InputFaceDetection element) {
		File image=element.file;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize=4;
		Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
		int R, G, B,value;
	    int pixel;
	    int sum=0;
	            
		for(int x=0;x<bmOptions.outWidth;x++)
			for(int y=0;y<bmOptions.outHeight;y++){
				pixel=bitmap.getPixel(x,y);
				R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	            value=(R+G+B)/3;
	            sum+=value;
			}
		return ((double)sum)/((double)(bmOptions.outWidth*bmOptions.outHeight));
	}

}
