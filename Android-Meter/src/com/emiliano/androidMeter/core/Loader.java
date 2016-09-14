package com.emiliano.androidMeter.core;

public interface Loader<Element> {
	void loadElement();
	void releaseElement();
	boolean isLoaded();
	Element getElement();
}
