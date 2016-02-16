package com.emiliano.evalfd;

import java.io.File;

public class InputFaceDetection {
	public File file;
	public int numberOfFaces;
	public FacePosition[] realPositions;
	
	public InputFaceDetection(File file, int numberOfFaces){
		this.file=file;
		this.numberOfFaces=numberOfFaces;
	}
	
	public InputFaceDetection(File file, FacePosition... realPositions){
		this.file=file;
		this.numberOfFaces=realPositions.length;
		this.realPositions=realPositions;
	}
	
	@Override
	public String toString() {
		return this.file.getPath();
	}
}
