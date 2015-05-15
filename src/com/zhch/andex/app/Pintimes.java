package com.zhch.andex.app;

import android.app.Application;
import android.telephony.TelephonyManager;

import com.facebook.FacebookSdk;

public class Pintimes extends Application {
	private static Pintimes s_instance;

	private String deviceId;

	public static Pintimes getInstance() {
		return s_instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		s_instance = this;
		
		FacebookSdk.sdkInitialize(getApplicationContext());
	}

	/**
	 * 设备标识
	 * @return
	 */
	public String getDeviceId() {
		if (null == deviceId) {
			TelephonyManager tm = (TelephonyManager) getSystemService(android.content.Context.TELEPHONY_SERVICE);
			deviceId = tm.getDeviceId();
			if (null == deviceId) {
				deviceId = tm.getSubscriberId();
			}
		}
		return deviceId;
	}
}
