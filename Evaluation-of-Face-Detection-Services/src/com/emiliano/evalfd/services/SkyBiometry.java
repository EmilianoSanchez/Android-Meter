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

public class SkyBiometry extends WebServiceClientComponent<InputFaceDetection,FaceDetectionResult> implements FaceDetectionComponent{
	
	private boolean gender;
	private boolean glasses;
	private boolean smiling;
	private boolean aggresiveDetector;

	public SkyBiometry(){
		this(false,false,false,false);
	}
	public SkyBiometry(boolean gender,boolean glasses , boolean smiling,boolean aggresiveDetector){
		this.gender=gender;
		this.glasses=glasses;
		this.smiling=smiling;
		this.aggresiveDetector=aggresiveDetector;
	}

	@Override
	protected String getUrl() {
		return "https://face.p.mashape.com/faces/detect?api_key=8b15613e37ad40cfa998b450f82b0ae9&api_secret=def36fd8b528405b8f63b21f856fb6c8";
	}

	@Override
	protected Method getMethod() {
		return Method.POST;
	}

	@Override
	protected void configureHttpClient(SyncHttpClient client) {
		client.addHeader("X-Mashape-Key", "KHSgHKCPshmshHGTwHV3MZPt1Zi1p1R6sM8jsnUc5ioRKTo3qk");
		client.addHeader("Content-Type", "application/x-www-form-urlencoded");
		client.addHeader("Accept", "application/json");
	}

	@Override
	public String getName() {
		String name=new String("Sky Biometry");
		if(this.gender || this.glasses || this.smiling || this.aggresiveDetector)
			name+=" with ";
		if(this.gender)
			name+="gender, ";
		if(this.glasses)
			name+="glasses,";
		if(this.smiling)
			name+="smiling,";	
		if(this.aggresiveDetector)
			name+="aggresiveDetector";
		return name;
	}

	@Override
	protected void setParams(RequestParams params, InputFaceDetection input) {
		try {
			params.put("files", input.file);
			String attributes=new String("");
			if(this.gender)
				attributes+="gender,";
			if(this.glasses)
				attributes+="glasses,";
			if(this.smiling)
				attributes+="smiling,";	
			if(!attributes.equals(""))
				params.put("attributes", attributes);
			if(this.aggresiveDetector)
				params.put("detector", "Aggressive");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FaceDetectionResult parseResponse(int statusCode, Header[] headers, JSONObject response) {
		FaceDetectionResultImpl result=new FaceDetectionResultImpl();
		try{
			JSONArray jfaces=response.getJSONArray("photos").getJSONObject(0).getJSONArray("tags");
			FaceImpl [] faces=new FaceImpl[jfaces.length()];
			for(int i=0;i<jfaces.length();i++){
				org.json.JSONObject jface=jfaces.getJSONObject(i);
				org.json.JSONObject jcenter=jface.getJSONObject("center");
				faces[i]=new FaceImpl();
				
				int width=(int)jface.getDouble("width");
				int height=(int)jface.getDouble("height");
				int centerX=(int)(jcenter.getDouble("x"));
				int centerY=(int)(jcenter.getDouble("y"));		
				
				faces[i].setFacePosition(new FacePosition(centerX-width/2,centerY+height/2,width,height));
			}
			result.setDetectedFaces(faces);
			result.setStringResult(response.toString());
		} catch (JSONException e) {
			Log.i("BlackBox","Error: "+e.getMessage());
			e.printStackTrace();
		}
		return result;
    }
}


