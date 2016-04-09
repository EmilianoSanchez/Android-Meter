package com.emiliano.androidTestTool.core.metrics.operation;

import android.content.Context;

//TO DO
public class DynamicContextProperties {

	public static enum PropertyName {

		RAMusage {
			public String toString() {
				return "Memory usage in %";
			}
		},
		RAMavailable {
			public String toString() {
				return "Free memory in MBs";
			}
		},
		CpuUsage, GPUUsage, DiskUsage, NetType, NetSubtype, NetUsage, NetLatency, NetUploadSpeed, NetDownloadSpeed, NetLinkSpeed
	}

	private Context context;

	public DynamicContextProperties(Context context) {
		this.context = context;
	}

	public Object getProperty(PropertyName property) {
		// TODO Auto-generated method stub
		return null;
	}

}
