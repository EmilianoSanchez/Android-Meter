package com.emiliano.evalfd;

import android.util.Log;

public class FacePosition {
	
	public FacePosition(int left,int top,int width,int height){
		this.left=left;
		this.top=top;
		this.width=width;
		this.height=height;
	}
	
	public FacePosition(double left,double top,double width,double height){
		this.left=(int)left;
		this.top=(int)top;
		this.width=(int)width;
		this.height=(int)height;
	}
	
	public int left;
	public int top;
	public int width;
	public int height;
	
	@Override
	public String toString() {
		return " \"facePosition\" : { \"left\" : "+left+" , \"top\" : "+top+" , \"width\" : "+width+" , \"height\" : "+height+" } ";
	}
	
	public int getArea(){
		return this.height*this.width;
	}
	
	public double getOverlappingRatio(FacePosition other){
		double overlappingRatio=0.0;
		if(other!=null){
			int mayorLeft=0;
			if(this.left<other.left)
				mayorLeft=other.left;
			else
				mayorLeft=this.left;
			int minorRight=0;
			if(this.left+this.width>other.left+other.width)
				minorRight=other.left+other.width;
			else
				minorRight=this.left+this.width;
			int overlapedWidth = 0;
			if(minorRight>mayorLeft)
				overlapedWidth = minorRight-mayorLeft;
			
			int mayorTop=0;
			if(this.top<other.top)
				mayorTop=other.top;
			else
				mayorTop=this.top;
			int minorBottom=0;
			if(this.top+this.height>other.top+other.height)
				minorBottom=other.top+other.height;
			else
				minorBottom=this.top+this.height;
			int overlapedHeight = 0;
			if(minorBottom>mayorTop)
				overlapedHeight = minorBottom-mayorTop;
			
			int overlapedArea = overlapedHeight*overlapedWidth;
			int fullArea = this.getArea()+other.getArea()-overlapedArea;
			overlappingRatio = ((double)overlapedArea / (double)fullArea);
		}
		return overlappingRatio;
	}
}