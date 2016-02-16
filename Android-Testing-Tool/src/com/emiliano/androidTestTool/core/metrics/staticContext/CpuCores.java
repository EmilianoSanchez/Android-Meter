package com.emiliano.androidTestTool.core.metrics.staticContext;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.metrics.Metric;
import com.emiliano.androidTestTool.core.metrics.staticContext.DeviceModel.Model;

import android.content.Context;
import android.util.Log;

public class CpuCores implements Metric<Context> {

	private static int[] mapping;
	
	static{
		mapping=new int[Model.values().length];
		mapping[Model.Lenovo_K50_T5.ordinal()]=8;
		mapping[Model.GT_I9300.ordinal()]=4;
		mapping[Model.C2104.ordinal()]=2;
	}
	
	@Override
	public String getName() {
		return "CPU cores";
	}

	@Override
	public Integer calculate(Context element) {
		Model model=(Model)(new DeviceModel().calculate(element));
		return mapping[model.ordinal()];
	}

}
