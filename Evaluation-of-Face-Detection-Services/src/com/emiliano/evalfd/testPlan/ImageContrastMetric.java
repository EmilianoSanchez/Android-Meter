package com.emiliano.evalfd.testPlan;

import java.io.File;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.evalfd.InputFaceDetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

//Contrast is the standard deviation of the intensity
//https://forum.processing.org/one/topic/calculate-image-contrast-using-root-mean-square-rms.html
public class ImageContrastMetric implements Metric<InputFaceDetection> {

	private ImageMeanIntensityMetric intensity=new ImageMeanIntensityMetric();
	
	@Override
	public String getName() {
		return "ImageContrastMetric";
	}

	@Override
	public Object calculate(InputFaceDetection element) {
		double meanIntensity=(Double) this.intensity.calculate(element);
		
		File image=element.file;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize=4;
		Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
		int R, G, B;
		double value;
	    int pixel;
	    double sum=0.0;
	            
		for(int x=0;x<bmOptions.outWidth;x++)
			for(int y=0;y<bmOptions.outHeight;y++){
				pixel=bitmap.getPixel(x,y);
				R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	            value=(R+G+B)/3;
	            sum+=(value-meanIntensity)*(value-meanIntensity);
			}
		return Math.sqrt(sum/((double)(bmOptions.outWidth*bmOptions.outHeight)));
	}

}
