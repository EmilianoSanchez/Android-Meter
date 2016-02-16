package com.emiliano.androidTestTool.core.components;

public interface Component<Input,Output> {
	public String getName();
	public Output execute(Input input);
}
