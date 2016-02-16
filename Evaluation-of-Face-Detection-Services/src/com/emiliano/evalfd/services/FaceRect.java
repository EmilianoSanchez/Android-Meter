package com.emiliano.evalfd.services;

import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


import com.emiliano.androidTestTool.core.components.WebServiceClientComponent;
import com.emiliano.evalfd.FaceDetectionResult;
import com.emiliano.evalfd.FaceDetectionResultImpl;
import com.emiliano.evalfd.FaceImpl;
import com.emiliano.evalfd.FacePosition;
import com.emiliano.evalfd.InputFaceDetection;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.Header;

public class FaceRect extends WebServiceClientComponent<InputFaceDetection,FaceDetectionResult> implements FaceDetectionComponent{

	private boolean features;

	public FaceRect(){
		this(false);
	}
	public FaceRect(boolean features){
		this.features=features;
	}
	
	@Override
	protected String getUrl() {
		return "https://apicloud-facerect.p.mashape.com/process-file.json";
	}

	@Override
	protected Method getMethod() {
		return Method.POST;
	}

	@Override
	protected void configureHttpClient(SyncHttpClient client) {
		client.addHeader("X-Mashape-Key", "KHSgHKCPshmshHGTwHV3MZPt1Zi1p1R6sM8jsnUc5ioRKTo3qk");
	}

	@Override
	public String getName() {
		if(features)
			return "Face Rect with features";
		else
			return "Face Rect";
	}

	@Override
	protected void setParams(RequestParams params, InputFaceDetection input) {
		try {
			params.put("image", input.file);
			if(features)
				params.put("features", "true");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FaceDetectionResult parseResponse(int statusCode, Header[] headers, JSONObject response) {
		Log.i("BlackBox",response.toString());
		FaceDetectionResultImpl result=new FaceDetectionResultImpl();
		try{
    		JSONArray jfaces=response.getJSONArray("faces");
    		FaceImpl[] faces=new FaceImpl[jfaces.length()];
    		for(int i=0;i<jfaces.length();i++){
    			org.json.JSONObject jface=jfaces.getJSONObject(i);
    			faces[i]=new FaceImpl();
    			faces[i].setFacePosition(new FacePosition(jface.getInt("x"),jface.getInt("y"),jface.getInt("width"),jface.getInt("height")));
    		}
    		result.setDetectedFaces(faces);
    		result.setStringResult(response.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
    }
}
