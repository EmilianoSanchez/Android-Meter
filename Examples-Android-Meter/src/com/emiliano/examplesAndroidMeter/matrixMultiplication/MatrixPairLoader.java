package com.emiliano.examplesAndroidMeter.matrixMultiplication;

import com.emiliano.androidMeter.core.Loader;

public class MatrixPairLoader implements Loader<MatrixPair>{

	int aRows; int aColums; int bColums;
	MatrixPair mPair;
	
	public MatrixPairLoader(int aRows, int aColums, int bColums){
		if (aRows < 1 || aColums < 1 | bColums < 1)
			throw new IllegalArgumentException("Matrix rows and colums length must be greater or equal than 1");
		this.aRows=aRows;
		this.aColums=aColums;
		this.bColums=bColums;
	}
	
	@Override
	public void loadElement() {
		
		mPair = new MatrixPair();
			mPair.matrixA = new float[aRows][aColums];
			for (int i = 0; i < aRows; i++)
				for (int j = 0; j < aColums; j++)
					mPair.matrixA[i][j] = (float) Math.random();
			mPair.matrixB = new float[aColums][bColums];
			for (int i = 0; i < aColums; i++)
				for (int j = 0; j < bColums; j++)
					mPair.matrixB[i][j] = (float) Math.random();

	}

	@Override
	public void releaseElement() {
		this.mPair=null;
	}

	@Override
	public boolean isLoaded() {
		if(mPair!=null)
			return true;
		return false;
	}

	@Override
	public MatrixPair getElement() {
		return mPair;
	}

}
