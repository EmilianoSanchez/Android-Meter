package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.context.DeviceModel;
import com.emiliano.androidTestTool.context.DeviceModel.ModelEnum;
import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.Metric;

import android.content.Context;

public class CpuArchitecture<Input, Output> implements Metric<TestPlan<Input, Output>> {

	public static enum Architecture {
		ARMv7,AArch64
	}

	private static Architecture[] mapping;
	
	static{
		mapping=new Architecture[ModelEnum.values().length];
		mapping[ModelEnum.Lenovo_K50_T5.ordinal()]=Architecture.AArch64;
		mapping[ModelEnum.GT_I9300.ordinal()]=Architecture.ARMv7;
		mapping[ModelEnum.C2104.ordinal()]=Architecture.ARMv7;
	}
	
	@Override
	public String getName() {
		return "CPU Architecture";
	}

	@Override
	public Architecture calculate(TestPlan<Input, Output> element) {
		ModelEnum model=new DeviceModel().getDeviceModelEnum();
		return mapping[model.ordinal()];
	}

}
