package com.emiliano.examplesatt.examples.matrix;

public class MatrixPair {
	public double [][] matrixA;
	public double [][] matrixB;
	
	public static MatrixPair generateRandom(int aRows,int aColums,int bColums){
		if(aRows<1 || aColums<1 | bColums<1)
			throw new IllegalArgumentException("Matrix rows and colums length must be greater or equal than 1");
		MatrixPair mPair=new MatrixPair();
		mPair.matrixA=new double[aRows][aColums];
		for(int i=0;i<aRows;i++)
			for(int j=0;j<aColums;j++)
				mPair.matrixA[i][j]=Math.random();
		mPair.matrixB=new double[aColums][bColums];
		for(int i=0;i<aColums;i++)
			for(int j=0;j<bColums;j++)
				mPair.matrixB[i][j]=Math.random();
		return mPair;
	};
}
