package com.emiliano.evalfd;

public class FaceExpression {
	
	public FaceExpression(String expressionName,float confidence){
		this.expressionName=expressionName;
		this.confidence=confidence;
	}
	
	public String expressionName;
	public float confidence;
	
	@Override
	public String toString() {
		return " \""+expressionName+"\" : "+confidence;
	}
}
