package com.allocate.ontime.business_logic.repository

import android.util.Log
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.network.OnTimeApi
import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.EditDeviceInfo
import javax.inject.Inject

class OnTimeRepository @Inject constructor(
    private val api: OnTimeApi,

    ) {
    suspend fun getDeviceInfo(): DataOrException<DeviceInfo, Exception> {
        val dataOrException = DataOrException<DeviceInfo, Exception>()
        try {
            dataOrException.data = api.getDeviceInfo()
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getAllDeviceInfo: ${dataOrException.e}")
        }
        return dataOrException
    }

    suspend fun postDeviceInfo(
        appInfo: AppInfo
    ): DataOrException<EditDeviceInfo, Exception> {
        val dataOrException = DataOrException<EditDeviceInfo, Exception>()
        try {
            dataOrException.data = api.postEditDeviceInfo(appInfo)
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getAllDeviceInfo: ${dataOrException.e}")
        }
        return dataOrException
    }
}