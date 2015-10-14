package com.emiliano.androidblackboxtool;

public interface BlackBox<Input,Output> {
	
	Output execute(Input input);
	
	void setInput(Input input);
	void execute();
	Output getOutput();
}
