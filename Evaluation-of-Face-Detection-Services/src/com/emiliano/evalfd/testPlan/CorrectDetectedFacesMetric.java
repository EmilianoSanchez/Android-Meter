package com.emiliano.evalfd.testPlan;

import com.emiliano.androidTestTool.core.components.Component;
import com.emiliano.androidTestTool.core.metrics.OperationMetric;
import com.emiliano.evalfd.Face;
import com.emiliano.evalfd.FaceDetectionResult;
import com.emiliano.evalfd.FacePosition;
import com.emiliano.evalfd.InputFaceDetection;

import android.util.Log;

public class CorrectDetectedFacesMetric implements OperationMetric<InputFaceDetection, FaceDetectionResult> {

	private FacePosition[] realPositions;
	private double threshold;
	
	public CorrectDetectedFacesMetric(){
		this(0.5);
	}
	public CorrectDetectedFacesMetric(double threshold) {
		this.threshold=threshold;
	}
	
	@Override
	public Object calculate(FaceDetectionResult element) {
		boolean[] detected=new boolean[realPositions.length];
		int correctDetections=0;
		Face[] detectedFaces=element.getDetectedFaces();
		for(Face face:detectedFaces){
			for(int i=0;i<detected.length;i++){
				if(detected[i]==false){
					if(realPositions[i].getOverlappingRatio(face.getFacePosition())>=this.threshold){
//						Log.i("EVAL", " "+realPositions[i].getOverlappingRatio(face.getFacePosition()));
						detected[i]=true;
						correctDetections++;
						break;
					}
				}
			}
		}
		return correctDetections;
	}

	@Override
	public String getName() {
		return "CorrectDetectedFacesMetric";
	}

	@Override
	public void beforeOperation(InputFaceDetection input,
			Component<InputFaceDetection, FaceDetectionResult> component) {
		this.realPositions=input.realPositions;
	}

}
