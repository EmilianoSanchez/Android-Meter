package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.staticContext.DeviceModel.Model;

import android.content.Context;

public class MemMark implements Metric<Context> {

	private static int[] mapping;
	
	static{
		mapping=new int[Model.values().length];
		mapping[Model.Lenovo_K50_T5.ordinal()]=5383;
		mapping[Model.GT_I9300.ordinal()]=1239;
		mapping[Model.C2104.ordinal()]=2282;
	}
	
	@Override
	public String getName() {
		return "Mem Mark";
	}

	@Override
	public Integer calculate(Context element) {
		Model model=(Model)(new DeviceModel().calculate(element));
		return mapping[model.ordinal()];
	}

}
