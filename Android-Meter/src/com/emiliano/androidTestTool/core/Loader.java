package com.emiliano.androidTestTool.core;

public interface Loader<Element> {
	void loadElement();
	void releaseElement();
	boolean isLoaded();
	Element getElement();
}
