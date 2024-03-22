package com.allocate.ontime.business_logic.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceUtility @Inject constructor(@ApplicationContext private val context: Context) {

    private lateinit var imei: String

    @SuppressLint("HardwareIds")
    fun getIMEI(): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // Use getImei() for Android 10 and higher
            imei = telephonyManager.imei
        } else {
            // Use getDeviceId() for Android 9 and below
            imei = telephonyManager.deviceId
        }
        return imei
    }

    @SuppressLint("HardwareIds")
    fun getMacAddress(context: Context): String {
        val wifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo? = wifiManager.connectionInfo
        return wifiInfo?.macAddress?.uppercase(Locale.ROOT) ?: "Mac address not available"
    }
}