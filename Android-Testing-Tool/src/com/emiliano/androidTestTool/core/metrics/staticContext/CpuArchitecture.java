package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.staticContext.DeviceModel.Model;

import android.content.Context;

public class CpuArchitecture implements Metric<Context> {

	public static enum Architecture {
		ARMv7,AArch64
	}

	private static Architecture[] mapping;
	
	static{
		mapping=new Architecture[Model.values().length];
		mapping[Model.Lenovo_K50_T5.ordinal()]=Architecture.AArch64;
		mapping[Model.GT_I9300.ordinal()]=Architecture.ARMv7;
		mapping[Model.C2104.ordinal()]=Architecture.ARMv7;
	}
	
	@Override
	public String getName() {
		return "CPU Architecture";
	}

	@Override
	public Architecture calculate(Context element) {
		Model model=(Model)(new DeviceModel().calculate(element));
		return mapping[model.ordinal()];
	}

}
