package com.emiliano.androidTestTool.core.metrics.staticContext;

import com.emiliano.androidTestTool.core.metrics.Metric;

import android.content.Context;
import android.os.Build;

public class DeviceModel implements Metric<Context> {
	
	public static enum Model {
		Lenovo_K50_T5,
		GT_I9300,
		C2104
	}

	@Override
	public String getName() {
		return "Device Model";
	}

	@Override
	public Model calculate(Context element) {
		String modelName=Build.MODEL;
		modelName=modelName.replace('-', '_');
		return Model.valueOf(modelName);
	}
}
