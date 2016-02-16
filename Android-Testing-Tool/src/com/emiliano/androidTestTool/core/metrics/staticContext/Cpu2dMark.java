package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.staticContext.DeviceModel.Model;

import android.content.Context;

public class Cpu2dMark implements Metric<Context> {

	private static int[] mapping;
	
	static{
		mapping=new int[Model.values().length];
		mapping[Model.Lenovo_K50_T5.ordinal()]=3598;
		mapping[Model.GT_I9300.ordinal()]=2524;
		mapping[Model.C2104.ordinal()]=1884;
	}
	
	@Override
	public String getName() {
		return "CPU 2D Mark";
	}

	@Override
	public Integer calculate(Context element) {
		Model model=(Model)(new DeviceModel().calculate(element));
		return mapping[model.ordinal()];
	}

}
