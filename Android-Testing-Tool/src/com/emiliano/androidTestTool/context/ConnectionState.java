package com.emiliano.androidTestTool.context;

import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;

public class ConnectionState{
	
	public enum NetworkState{
		MOBILE_CONNECTION,
		WIFI_CONNECTION,
		OTHER_CONNECTION,
		NO_CONNECTION,
		UNKNOWN
	}
	
	public enum NetworkGroup{
		_2G_CONNECTION,
		_3G_CONNECTION,
		_4G_CONNECTION,
		WIFI_CONNECTION,
		OTHER_CONNECTION,
		NO_CONNECTION,
		UNKNOWN
	}
	public enum NetworkType{
		NETWORK_TYPE_UNKNOWN,
		NETWORK_TYPE_GPRS,
		NETWORK_TYPE_EDGE,
		NETWORK_TYPE_UMTS,
		NETWORK_TYPE_CDMA,
		NETWORK_TYPE_EVDO_0,
		NETWORK_TYPE_EVDO_A,
		NETWORK_TYPE_1xRTT,
		NETWORK_TYPE_HSDPA,
		NETWORK_TYPE_HSUPA,
		NETWORK_TYPE_HSPA,
		NETWORK_TYPE_IDEN,
		NETWORK_TYPE_EVDO_B,
		NETWORK_TYPE_LTE,
		NETWORK_TYPE_EHRPD,
		NETWORK_TYPE_HSPAP,
		NETWORK_TYPE_GSM,
		NETWORK_TYPE_TD_SCDMA,
		NETWORK_TYPE_IWLAN,
		NETWORK_TYPE_WIFI,
		NETWORK_TYPE_OTHER,
		NO_CONNECTION,
		UNKNOWN
	}
	
	public ConnectionState(){
		this.networkState=NetworkState.UNKNOWN;
		this.networkGroup=NetworkGroup.UNKNOWN;
		this.networkType=NetworkType.UNKNOWN;
		this.linkSpeed=0;
	}
	
	public NetworkState networkState;
	public NetworkGroup networkGroup;
	public NetworkType networkType;

	public int linkSpeed;
	
//	public int latency;
//	public int uploadSpeed;
//	public int downloadSpeed;
	
	public NetworkInfo networkInfo;
	public WifiInfo wifiInfo;
	public MobileNetInfo mobileNetInfo;
	
	public boolean isConnected(){
		return !(networkState==NetworkState.NO_CONNECTION || networkState==NetworkState.UNKNOWN);
	}

	@Override
	public String toString() {
		return this.networkState.toString()+" "+this.networkGroup.toString()+" "+this.networkType.toString()+" Link speed:"+this.linkSpeed;
	}
	
	public static class MobileNetInfo{
		public MobileNetInfo(TelephonyManager telephonyManager){
			this.networkType=telephonyManager.getNetworkType();
			this.networkOperator=telephonyManager.getNetworkOperator();
			this.networkOperatorName=telephonyManager.getNetworkOperatorName();
		};
		public String networkOperator;
		public String networkOperatorName;
		public int networkType;
	}
}
