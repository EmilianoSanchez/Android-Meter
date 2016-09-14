package com.emiliano.androidMeter.core;

public class SimpleLoader<Element> implements Loader<Element>{

	private Element element;
	
	public SimpleLoader(Element element) {
		this.element=element;
	}
	
	@Override
	public void loadElement() {}

	@Override
	public void releaseElement() {}

	@Override
	public boolean isLoaded() {
		return true;
	}

	@Override
	public Element getElement() {
		return element;
	}


}
