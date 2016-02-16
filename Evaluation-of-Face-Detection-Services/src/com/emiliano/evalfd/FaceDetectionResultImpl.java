package com.emiliano.evalfd;


public class FaceDetectionResultImpl implements FaceDetectionResult{

	private Face[] detectedFaces;
	private String stringResult;
	
	@Override
	public Face[] getDetectedFaces() {
		return detectedFaces;
	}

	@Override
	public String getStringResult() {
		return stringResult;
	}

	public void setDetectedFaces(Face[] detectedFaces) {
		this.detectedFaces = detectedFaces;
	}

	public void setStringResult(String stringResult) {
		this.stringResult = stringResult;
	}

	public String toString(){
		StringBuilder builder=new StringBuilder("[");
		for(Face face:this.getDetectedFaces()){
			builder.append("{");
			builder.append(face.toString());
			builder.append("},");
		}
		builder.append("]");
		return builder.toString();
	}
}
