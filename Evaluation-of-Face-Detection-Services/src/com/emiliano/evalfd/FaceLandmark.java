package com.emiliano.evalfd;

public class FaceLandmark {
	public enum LandmarkType{
		unknown,
		pupilLeft,
        pupilRight,
        noseTip,
        mouthLeft,
        mouthRight,
        eyebrowLeftOuter,
        eyebrowLeftInner,
        eyeLeftOuter,
        eyeLeftTop,
        eyeLeftBottom,
        eyeLeftInner,
        eyebrowRightInner,
        eyebrowRightOuter,
        eyeRightInner,
        eyeRightTop,
        eyeRightBottom,
        eyeRightOuter,
        noseRootLeft,
        noseRootRight,
        noseLeftAlarTop,
        noseRightAlarTop,
        noseLeftAlarOutTip,
        noseRightAlarOutTip,
        upperLipTop,
        upperLipBottom,
        underLipTop,
        underLipBottom
	}
	
	public FaceLandmark(int x, int y){
		this.type=LandmarkType.unknown;
		this.x=x;
		this.y=y;
	}
	
	public FaceLandmark(LandmarkType type, int x, int y){
		this.type=type;
		this.x=x;
		this.y=y;
	}
	
	public LandmarkType type;
	public int x;
	public int y;
	
	@Override
	public String toString() {
		return " \""+type.name()+"\" : { \"x\" : "+x+" , \"y\" : "+y+" } ";
	}
}
