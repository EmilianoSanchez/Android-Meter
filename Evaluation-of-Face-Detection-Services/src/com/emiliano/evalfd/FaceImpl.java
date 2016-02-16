package com.emiliano.evalfd;

import java.util.Set;

import com.google.android.gms.vision.face.Landmark;

public class FaceImpl implements Face{

	private FacePosition facePosition;
	private FaceOrientation faceOrientation;
	private Set<FaceLandmark> faceLandmarks;
	private Float isLeftEyeOpenConfidence;
	private Float isRightEyeOpenConfidence;
	private Float isSmilingConfidence;
	private Float isFemaleConfidence;
	private Set<FaceExpression> faceExpressions;
	private Integer age;
	private FaceRace race;

	public void setFacePosition(FacePosition facePosition) {
		this.facePosition = facePosition;
	}

	public void setFaceOrientation(FaceOrientation faceOrientation) {
		this.faceOrientation = faceOrientation;
	}

	public void setFaceLandmarks(Set<FaceLandmark> faceLandmarks) {
		this.faceLandmarks = faceLandmarks;
	}

	public void setIsLeftEyeOpenConfidence(Float isLeftEyeOpenConfidence) {
		this.isLeftEyeOpenConfidence = isLeftEyeOpenConfidence;
	}

	public void setIsRightEyeOpenConfidence(Float isRightEyeOpenConfidence) {
		this.isRightEyeOpenConfidence = isRightEyeOpenConfidence;
	}

	public void setIsSmilingConfidence(Float isSmilingConfidence) {
		this.isSmilingConfidence = isSmilingConfidence;
	}
	
	public void setIsFemaleConfidence(Float isFemaleConfidence) {
		this.isFemaleConfidence = isFemaleConfidence;
	}
	
	public void setFaceExpressions(Set<FaceExpression> faceExpressions) {
		this.faceExpressions = faceExpressions;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setRace(FaceRace race) {
		this.race = race;
	}



	@Override
	public FacePosition getFacePosition() {
		return facePosition;
	}

	@Override
	public FaceOrientation getFaceOrientation() {
		return faceOrientation;
	}

	@Override
	public Set<FaceLandmark> getFaceLandmarks() {
		return faceLandmarks;
	}



	@Override
	public Float getIsLeftEyeOpenConfidence() {
		return isLeftEyeOpenConfidence;
	}

	@Override
	public Float getIsRightEyeOpenConfidence() {
		return isRightEyeOpenConfidence;
	}

	@Override
	public Float getIsSmilingConfidence() {
		return isSmilingConfidence;
	}

	@Override
	public Float getIsFemaleConfidence() {
		return isFemaleConfidence;
	}
	
	@Override
	public Set<FaceExpression> getFaceExpressions() {
		return faceExpressions;
	}

	@Override
	public Integer getAge() {
		return age;
	}

	@Override
	public FaceRace getRace() {
		return race;
	}
	
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder(" \"face\" : {");
		if(facePosition!=null){
			builder.append(facePosition.toString());
			builder.append(",");
		}
		if(faceOrientation!=null){
			builder.append(faceOrientation.toString());
			builder.append(",");
		}
		if(faceLandmarks!=null && faceLandmarks.size()>0){
			builder.append("\"faceLandmarks\": {");
			for(FaceLandmark landmark:faceLandmarks){
				builder.append(landmark.toString());
				builder.append(" ,");
			}
			builder.append("},");
		}
		if(isLeftEyeOpenConfidence!=null){
			builder.append("\"isLeftEyeOpenConfidence\" : "+isLeftEyeOpenConfidence);
			builder.append(",");
		}
		if(isRightEyeOpenConfidence!=null){
			builder.append("\"isRightEyeOpenConfidence\" : "+isRightEyeOpenConfidence);
			builder.append(",");
		}
		if(isSmilingConfidence!=null){
			builder.append("\"isSmilingConfidence\" : "+isSmilingConfidence);
			builder.append(",");
		}
		if(isFemaleConfidence!=null){
			builder.append("\"isFemaleConfidence\" : "+isFemaleConfidence);
			builder.append(",");
		}
		if(faceExpressions!=null && faceExpressions.size()>0){
			builder.append("\"faceExpressions\": {");
			for(FaceExpression expression:faceExpressions){
				builder.append(expression.toString());
				builder.append(" ,");
			}
			builder.append("},");
		}
		if(age!=null){
			builder.append("\"age\" : "+age);
			builder.append(",");
		}
		if(race!=null){
			builder.append("\"race\" : "+race.name());
			builder.append(",");
		}
		builder.append("}");
		return builder.toString();
	}
}
