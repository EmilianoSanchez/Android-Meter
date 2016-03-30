package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.context.DeviceModel;
import com.emiliano.androidTestTool.context.DeviceModel.ModelEnum;
import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.Metric;

import android.content.Context;

public class CpuMark<Input, Output> implements Metric<TestPlan<Input, Output>> {

	private static int[] mapping;
	
	static{
		mapping=new int[ModelEnum.values().length];
		mapping[ModelEnum.Lenovo_K50_T5.ordinal()]=151265;
		mapping[ModelEnum.GT_I9300.ordinal()]=9354;
		mapping[ModelEnum.C2104.ordinal()]=3559;
	}
	
	@Override
	public String getName() {
		return "CPU Mark";
	}

	@Override
	public Integer calculate(TestPlan<Input, Output> element) {
		ModelEnum model=new DeviceModel().getDeviceModelEnum();
		return mapping[model.ordinal()];
	}

}
