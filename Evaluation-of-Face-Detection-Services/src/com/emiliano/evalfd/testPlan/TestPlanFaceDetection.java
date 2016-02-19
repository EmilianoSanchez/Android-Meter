package com.emiliano.evalfd.testPlan;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.ComponentNameMetric;
import com.emiliano.androidTestTool.core.metrics.InputNameMetric;
import com.emiliano.androidTestTool.core.metrics.OperationNameMetric;
import com.emiliano.androidTestTool.core.metrics.ResponseTimeMetric;
import com.emiliano.evalfd.FaceDetectionResult;
import com.emiliano.evalfd.InputFaceDetection;
import com.emiliano.evalfd.services.GMSFaceAPI;
import com.emiliano.evalfd.services.SkyBiometry;

public class TestPlanFaceDetection extends TestPlan<InputFaceDetection, FaceDetectionResult>{
	@SuppressWarnings("unchecked")
	public TestPlanFaceDetection(Context context){
		super("Test plan face detection",context);
		this.setDelayBetweenOperations(10);
		
//		this.addBlackBoxes(new BlackBoxFaceRect());
//		this.addBlackBoxes(new BlackBoxFaceRect(true));
//		this.addBlackBoxes(new BlackBoxSkyBiometry());
//		this.addComponents(new BlackBoxSkyBiometry(false,false,false,true));
		
		this.addComponents(new GMSFaceAPI(context,true,true,true));
//		this.addComponents(new GMSFaceAPI(context,true,true,false));
//		this.addComponents(new GMSFaceAPI(context,true,false,true));
//		this.addComponents(new GMSFaceAPI(context,true,false,false));
//		this.addComponents(new GMSFaceAPI(context,false,true,true));
//		this.addComponents(new GMSFaceAPI(context,false,true,false));
//		this.addComponents(new GMSFaceAPI(context,false,false,true));
//		this.addComponents(new GMSFaceAPI(context,false,false,false));
		
		this.addInputs();
		
		this.addComponentMetrics(new ComponentNameMetric());
		
		this.addInputMetrics(new InputNameMetric());
		this.addInputMetrics(new ImageNumberOfFacesMetric());
		this.addInputMetrics(new ImageFormatMetric());
		this.addInputMetrics(new ImageSizeMetric());
		this.addInputMetrics(new ImageNumberOfPixelsMetric());
		this.addInputMetrics(new ImageMeanIntensityMetric());
		this.addInputMetrics(new ImageContrastMetric());
//		this.addInputMetrics(new ImageEntropyMetric());
		
		this.addOperationMetrics(new ResponseTimeMetric());
		this.addOperationMetrics(new DetectedFacesMetric());
//		this.addOperationMetrics(new CorrectDetectedFacesMetric());
	}
	
	private void addInputs(){
		List<InputFaceDetection> list;
//		if(Build.MODEL.equals("C2104"))
//			list=FDDB_DataSetUtils.getInputFromEllipseList("/storage/sdcard0/FDDB-folds-01/FDDB-fold-01-ellipseList.txt");
//		else
//			list=FDDB_DataSetUtils.getInputFromEllipseList("/storage/emulated/0/FDDB-folds-01/FDDB-fold-01-ellipseList.txt");
//		for(InputFaceDetection input:list){
//			this.addInputs(input);
//		}
		
		
		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FDDB-folds-01/2002_07_19_big_img_381.jpg"),1));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FDDB-folds-01/2002_08_11_big_img_591.jpg"),2));
		
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection1.jpg"),1));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection2.jpg"),1));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection3.jpg"),2));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection4.jpg"),3));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection5.jpg"),2));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection6.jpg"),6));
		
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection7.jpg"),2));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection8.jpg"),4));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection9.jpg"),2));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection10.jpg"),0));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/detection11.jpg"),0));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/profile-left.jpg"),1));
//		this.addInputs(new InputFaceDetection(new File("/storage/emulated/0/FaceDetection/profile-right.jpg"),1));
		
	}
}
