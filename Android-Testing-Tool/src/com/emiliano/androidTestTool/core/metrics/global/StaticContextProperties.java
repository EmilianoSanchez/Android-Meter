package com.emiliano.androidTestTool.core.metrics.global;

import com.emiliano.androidTestTool.core.metrics.operation.DynamicContextProperties.PropertyName;

import android.content.Context;

public class StaticContextProperties {
	
	public static enum PropertyName {
		InstructionSet, SoC, CPU, CPUbits, GPUcores, RAMcapacity, RAMtype, Level1cacheMemory, ProcessTechnology, GPU, RAMchannels, Level0cacheMemory, CPUcores, Level3cacheMemory, CPUfrequency, Level2cacheMemory, GPUfrequency, RAMfrequency
	}

	public static Object getProperty(PropertyName property) {
		// TODO Auto-generated method stub
		return null;
	}

}
