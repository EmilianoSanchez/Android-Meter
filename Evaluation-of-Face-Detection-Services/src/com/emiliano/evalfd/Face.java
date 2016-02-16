package com.emiliano.evalfd;

import java.util.Set;

public interface Face {
	
	FacePosition getFacePosition();
	FaceOrientation getFaceOrientation();
	Set<FaceLandmark> getFaceLandmarks();
	Float getIsLeftEyeOpenConfidence();
	Float getIsRightEyeOpenConfidence();
	Float getIsSmilingConfidence();
	Float getIsFemaleConfidence();
	Set<FaceExpression> getFaceExpressions(); 
	Integer getAge();
	FaceRace getRace();

}
