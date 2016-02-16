package com.emiliano.evalfd;

public class FaceOrientation {
	
	public static enum Orientation{ FRONTAL, PROFILE_LEFT,PROFILE_RIGHT, UNKNOWN };
	
	public FaceOrientation(Orientation orientation){
		this.orientation=orientation;
		float [] angleVector=FaceOrientation.getOrientationAngleVector(orientation);
		this.yaw=angleVector[0];
		this.roll=angleVector[1];
		this.pitch=angleVector[2];
	}
	
	public FaceOrientation(float yaw,float roll,float pitch){
		this.yaw=yaw;
		this.roll=roll;
		this.pitch=pitch;
		this.orientation=FaceOrientation.getOrientationHighLevel(yaw, roll, pitch);
	}
	
	public Orientation orientation;
	public float yaw;
	public float roll;
	public float pitch;
	
	public static float[] getOrientationAngleVector(Orientation orientation){
		switch(orientation){
		case FRONTAL:
			return new float[]{0.0f,0.0f,0.0f};
		case PROFILE_LEFT:
			return new float[]{45.0f,0.0f,0.0f};
		case PROFILE_RIGHT:
			return new float[]{-45.0f,0.0f,0.0f};
		}
		return new float[]{0.0f,0.0f,0.0f};		
	}
	public static Orientation getOrientationHighLevel(float yaw,float roll,float pitch){
		if(yaw>30.0f)
			return Orientation.PROFILE_LEFT;
		else
			if(yaw<-30.0f)
				return Orientation.PROFILE_RIGHT;
			else
				return Orientation.FRONTAL;
	}
	
	@Override
	public String toString() {
		return " \"faceOrientation\" : { \"orientation\" : "+orientation.name()+" , \"yaw\" : "+yaw+" , \"roll\" : "+roll+" , \"pitch\" : "+pitch+" } ";
	}
}
