package com.emiliano.androidTestTool.context;

import java.util.LinkedList;
import java.util.List;

import com.emiliano.androidTestTool.context.ConnectionState.MobileNetInfo;
import com.emiliano.androidTestTool.context.ConnectionState.NetworkGroup;
import com.emiliano.androidTestTool.context.ConnectionState.NetworkState;
import com.emiliano.androidTestTool.context.ConnectionState.NetworkType;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class ConnectionManager {

	private List<OnConnectionStateChangeListener> listeners;

	private ConnectivityManager connectivityManager;
	private TelephonyManager telephonyManager;
	private WifiManager wifiManager;

	private ConnectionState currentState;

	public ConnectionManager(Context androidContext) {
		this.listeners = new LinkedList<OnConnectionStateChangeListener>();

		this.connectivityManager = (ConnectivityManager) androidContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		this.telephonyManager = (TelephonyManager) androidContext.getSystemService(Context.TELEPHONY_SERVICE);
		this.wifiManager = (WifiManager) androidContext.getSystemService(Context.WIFI_SERVICE);

		androidContext.registerReceiver(new ConnectivityChangeReceiver(),
				new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

		currentState = new ConnectionState();
	}

	public ConnectionState getCurrentState() {
		if (currentState.isConnected()) {
			currentState.networkInfo = connectivityManager.getActiveNetworkInfo();
			if (currentState.networkState == NetworkState.WIFI_CONNECTION) {
				currentState.wifiInfo = wifiManager.getConnectionInfo();
				currentState.linkSpeed = wifiManager.getConnectionInfo().getLinkSpeed();
			} else if (currentState.networkState == NetworkState.MOBILE_CONNECTION)
				currentState.mobileNetInfo = new MobileNetInfo(telephonyManager);
		}
		return currentState;
	}

	public void setOnConnectionChangeListener(OnConnectionStateChangeListener listener) {
		listeners.add(listener);
	}

	private void notifyConnectionStateChange(ConnectionState newState) {
		for (OnConnectionStateChangeListener listener : listeners) {
			listener.onConnectionStateChange(newState);
		}
	}

	private class ConnectivityChangeReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					NetworkInfo networkInfo = (NetworkInfo) extras.get(ConnectivityManager.EXTRA_NETWORK_INFO);

					currentState.networkInfo = networkInfo;
					currentState.wifiInfo = null;
					currentState.mobileNetInfo = null;

					if (networkInfo.isConnected()) {
						switch (networkInfo.getType()) {
						case ConnectivityManager.TYPE_WIFI:
							currentState.networkState = NetworkState.WIFI_CONNECTION;
							currentState.networkGroup = NetworkGroup.WIFI_CONNECTION;
							currentState.networkType = NetworkType.NETWORK_TYPE_WIFI;
							currentState.wifiInfo = wifiManager.getConnectionInfo();
							currentState.linkSpeed = currentState.wifiInfo.getLinkSpeed();
							break;
						case ConnectivityManager.TYPE_MOBILE:
							currentState.networkState = NetworkState.MOBILE_CONNECTION;
							currentState.mobileNetInfo = new MobileNetInfo(telephonyManager);
							switch (networkInfo.getSubtype()) {
							case TelephonyManager.NETWORK_TYPE_GPRS:
								currentState.networkGroup = NetworkGroup._2G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_GPRS;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_EDGE:
								currentState.networkGroup = NetworkGroup._2G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_EDGE;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_CDMA:
								currentState.networkGroup = NetworkGroup._2G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_CDMA;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_1xRTT:
								currentState.networkGroup = NetworkGroup._2G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_1xRTT;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_IDEN:
								currentState.networkGroup = NetworkGroup._2G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_IDEN;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_UMTS:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_UMTS;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_EVDO_0:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_EVDO_0;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_EVDO_A:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_EVDO_A;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_HSDPA:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_HSDPA;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_HSUPA:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_HSUPA;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_HSPA:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_HSPA;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_EVDO_B:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_EVDO_B;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_EHRPD:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_EHRPD;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_HSPAP:
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_HSPAP;
								currentState.linkSpeed = 0;
								break;
							case TelephonyManager.NETWORK_TYPE_LTE:
								currentState.networkGroup = NetworkGroup._4G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_LTE;
								currentState.linkSpeed = 0;
								break;
							case 16:// NETWORK_TYPE_GSM
								currentState.networkGroup = NetworkGroup._2G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_GSM;
								currentState.linkSpeed = 0;
							case 17:// NETWORK_TYPE_TD_SCDMA
								currentState.networkGroup = NetworkGroup._3G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_TD_SCDMA;
								currentState.linkSpeed = 0;
							case 18:// NETWORK_TYPE_IWLAN
								currentState.networkGroup = NetworkGroup._4G_CONNECTION;
								currentState.networkType = NetworkType.NETWORK_TYPE_IWLAN;
								currentState.linkSpeed = 0;
								break;
							default:// case 0
								currentState.networkGroup = NetworkGroup.UNKNOWN;
								currentState.networkType = NetworkType.UNKNOWN;
								currentState.linkSpeed = 0;
							}
						default:
							currentState.networkGroup = NetworkGroup.OTHER_CONNECTION;
							currentState.networkType = NetworkType.UNKNOWN;
							currentState.linkSpeed = 0;
						}
					} else {
						currentState.networkState = NetworkState.NO_CONNECTION;
						currentState.networkGroup = NetworkGroup.NO_CONNECTION;
						currentState.networkType = NetworkType.NO_CONNECTION;
						currentState.linkSpeed = 0;
					}
					notifyConnectionStateChange(currentState);
				}
			}
		}

	}

	// private static int mobileLinkSpeeds[];
	// private static NetworkGroup networkGroups[];
	// private static final int numNetworkTypes=19;
	//
	// static{
	// mobileLinkSpeeds=new int[numNetworkTypes];
	//
	// }
	//
	// MobileStaticInfo{
	// int networkType;
	// int networkGroup
	// int linkSpeed;
	//
	// MobileStaticInfo(){
	//
	// }
	// }
	// private static MobileStaticInfo[] mobileNetworks

}
