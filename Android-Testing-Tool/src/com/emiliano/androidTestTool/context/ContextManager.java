package com.emiliano.androidTestTool.context;

import android.content.Context;

public class ContextManager {

	private ContextState currentContextState;

	private ConnectionManager connectionManager;
	private BatteryManager batteryManager;
	
	// private ActivityManager activityManager;
	// private LocationManager locationManager;
	// private NetworkStatsManager networkStatsManager;
	// private UsageStatsManager usageStatsManager;

	public ContextManager(Context androidContext) {
		this.currentContextState = new ContextState(null,null);

		this.connectionManager=new ConnectionManager(androidContext);
		this.connectionManager.setOnConnectionChangeListener(new OnConnectionStateChangeListener() {
			@Override
			public void onConnectionStateChange(ConnectionState newConnectionState) {
				currentContextState.connectionState=newConnectionState;
			}
		});
		this.batteryManager=new BatteryManager(androidContext);
		this.batteryManager.setOnBatteryStateChangeListener(new OnBatteryStateChangeListener() {
			@Override
			public void onBatteryStateChange(BatteryState newBatteryState) {
				currentContextState.batteryState=newBatteryState;
			}
		});
		
		// this.activityManager=(ActivityManager)
		// this.androidContext.getSystemService(Context.ACTIVITY_SERVICE);
		// this.locationManager=(LocationManager)
		// this.androidContext.getSystemService(Context.LOCATION_SERVICE);
		// this.networkStatsManager=(NetworkStatsManager)
		// this.androidContext.getSystemService(Context.NETWORK_STATS_SERVICE);
		// this.usageStatsManager=(UsageStatsManager)
		// this.androidContext.getSystemService(Context.USAGE_STATS_SERVICE);	
	}

	public ContextState getContextState() {
		return currentContextState;
	};

	public void setOnContextChangeListener(OnContextChangeListener listener) {
		this.connectionManager.setOnConnectionChangeListener(listener);
		this.batteryManager.setOnBatteryStateChangeListener(listener);
	}

	
}
