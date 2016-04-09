package com.emiliano.androidTestTool.context;

public class ContextState {

	public ContextState(BatteryState batteryState, ConnectionState connectionState) {
		this.batteryState = batteryState;
		this.connectionState = connectionState;
	}

	public ConnectionState connectionState;
	public BatteryState batteryState;
}
