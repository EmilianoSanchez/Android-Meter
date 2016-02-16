package com.emiliano.evalfd.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.SparseArray;

import com.emiliano.evalfd.FaceDetectionResult;
import com.emiliano.evalfd.FaceDetectionResultImpl;
import com.emiliano.evalfd.FaceImpl;
import com.emiliano.evalfd.FacePosition;
import com.emiliano.evalfd.InputFaceDetection;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class GMSFaceAPI implements FaceDetectionComponent{

	private FaceDetector detector;
	private Context context;
	private boolean allLandmarks;
	private boolean allClassifications;
	private boolean accurateMode;
	
	public GMSFaceAPI(Context context){
		this(context, false, false, false);
	}
	
	public GMSFaceAPI(Context context,boolean allLandmarks,boolean allClassifications,boolean accurateMode){
		this.context=context;
		this.allLandmarks=allLandmarks;
		this.allClassifications=allClassifications;
		this.accurateMode=accurateMode;
		buildDetector();
	}
	
	private void buildDetector() {
		FaceDetector.Builder builder = new FaceDetector.Builder(context)
        .setTrackingEnabled(false)
        .setProminentFaceOnly(false);
		if(allLandmarks)
			builder.setLandmarkType(FaceDetector.ALL_LANDMARKS);
		else
			builder.setLandmarkType(FaceDetector.NO_LANDMARKS);
		if(allClassifications)
			builder.setClassificationType(FaceDetector.ALL_CLASSIFICATIONS);
		else
			builder.setClassificationType(FaceDetector.NO_CLASSIFICATIONS);
		if(accurateMode)
			builder.setMode(FaceDetector.ACCURATE_MODE);
		else
			builder.setMode(FaceDetector.FAST_MODE);
        
        this.detector=builder.build();
	}

	@Override
	public String getName() {
		String name=new String("GMS Face API");
		if(this.allLandmarks || this.allClassifications || this.accurateMode)
			name+=" with ";
		if(this.allLandmarks)
			name+="allLandmarks, ";
		if(this.allClassifications)
			name+="allClassifications,";
		if(this.accurateMode)
			name+="accurateMode,";	
		return name;
	}

	@Override
	public FaceDetectionResult execute(InputFaceDetection input) {
		FaceDetectionResultImpl result=new FaceDetectionResultImpl();
		try {
			InputStream stream;
			stream = new FileInputStream(input.file);
	        Bitmap bitmap = BitmapFactory.decodeStream(stream);
	        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
	        SparseArray<Face> gfaces = detector.detect(frame);
			FaceImpl [] faces=new FaceImpl[gfaces.size()];
			for(int i=0;i<gfaces.size();i++){
				Face gface=gfaces.get(i);
				
				int left=0;
				int top=0;
				int width=0;
				int height=0;
				if(gface!=null && gface.getPosition()!=null){
					left=(int)gface.getPosition().x;
					top=(int)gface.getPosition().y;
					width=(int)gface.getWidth();
					height=(int)gface.getHeight();
				}
				
				faces[i]=new FaceImpl();
				faces[i].setFacePosition(new FacePosition(left,top,width,height));
			}
			result.setDetectedFaces(faces);
			result.setStringResult(gfaces.toString());
		} catch (FileNotFoundException e) {
			Log.i("BlackBox","Error: "+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
