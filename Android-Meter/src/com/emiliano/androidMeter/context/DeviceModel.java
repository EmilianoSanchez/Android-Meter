package com.emiliano.androidMeter.context;

import android.os.Build;

public class DeviceModel {
	public String manufacturer;
	public String model;

	public DeviceModel() {
		this(Build.MANUFACTURER, Build.MODEL);
	}

	public DeviceModel(String manufacturer, String model) {
		this.manufacturer = manufacturer;
		this.model = model;
	}

	@Override
	public String toString() {
		return manufacturer + " " + model;
	}

	public ModelEnum getDeviceModelEnum() {
		String aux = model.replace('-', '_');
		return ModelEnum.valueOf(aux);
	}

	public static enum ModelEnum {
		Lenovo_K50_T5, GT_I9300, C2104
	};
}
