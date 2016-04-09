package com.emiliano.androidTestTool.context;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;

public class BatteryManager {

	private List<OnBatteryStateChangeListener> listeners;
	private android.os.BatteryManager batteryManager;

	public BatteryManager(Context androidContext) {
		this.listeners = new LinkedList<OnBatteryStateChangeListener>();

		this.batteryManager = (android.os.BatteryManager) androidContext.getSystemService(Context.BATTERY_SERVICE);
	}

	public void setOnBatteryStateChangeListener(OnBatteryStateChangeListener listener) {
		listeners.add(listener);
	}

	private void notifyConnectionStateChange(BatteryState newState) {
		for (OnBatteryStateChangeListener listener : listeners) {
			listener.onBatteryStateChange(newState);
		}
	}

}
