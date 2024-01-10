package com.allocate.ontime.business_logic.repository

import android.util.Log
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.network.OnTimeApi
import com.allocate.ontime.presentation_logic.model.AppInfo
import retrofit2.Response
import javax.inject.Inject

class OnTimeRepository @Inject constructor(
    private val api: OnTimeApi,

    ) {
    private val dataOrException = DataOrException<DeviceInfo, Boolean, Exception>()
    val dataOrException1 = DataOrException<Response<AppInfo>, Boolean, Exception>()

    suspend fun getDeviceInfo(): DataOrException<DeviceInfo, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getDeviceInfo()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getAllDeviceInfo: ${dataOrException.e}")
        }
        return dataOrException
    }

    suspend fun postDeviceInfo(
    appInfo: AppInfo
    ): DataOrException<Response<AppInfo>, Boolean, Exception> {
        try {
            dataOrException1.loading = false
            dataOrException1.data = api.postEditDeviceInfo(appInfo)
            if (dataOrException1.data.toString().isNotEmpty()) dataOrException1.loading = false
        } catch (exception: Exception) {
            dataOrException1.e = exception
            Log.d("EXC", "getAllDeviceInfo: ${dataOrException1.e}")
        }
        return dataOrException1
    }


}