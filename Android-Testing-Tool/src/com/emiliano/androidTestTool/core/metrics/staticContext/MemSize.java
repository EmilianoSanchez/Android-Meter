package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.staticContext.DeviceModel.Model;

import android.content.Context;

//Mem size in Megabytes
public class MemSize implements Metric<Context> {

	private static int[] mapping;
	
	static{
		mapping=new int[Model.values().length];
		mapping[Model.Lenovo_K50_T5.ordinal()]=1886;
		mapping[Model.GT_I9300.ordinal()]=820;
		mapping[Model.C2104.ordinal()]=829;
	}
	
	@Override
	public String getName() {
		return "Mem Size";
	}

	@Override
	public Integer calculate(Context element) {
		Model model=(Model)(new DeviceModel().calculate(element));
		return mapping[model.ordinal()];
	}

}
