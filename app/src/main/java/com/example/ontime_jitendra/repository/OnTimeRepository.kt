package com.example.ontime_jitendra.repository

import android.util.Log
import androidx.compose.material3.AlertDialogDefaults
import com.example.ontime_jitendra.data.DataOrException
import com.example.ontime_jitendra.model.DeviceInfo
import com.example.ontime_jitendra.network.OnTimeApi
import javax.inject.Inject

class OnTimeRepository @Inject constructor(private val api: OnTimeApi) {
    private val dataOrException = DataOrException<DeviceInfo, Boolean, Exception>()

    suspend fun getDeviceInfo(): DataOrException<DeviceInfo, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getDeviceInfo()
            Log.d("responses","${dataOrException.data}")
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false


        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getAllDeviceInfo: ${dataOrException.e}")
        }
        return dataOrException

    }
}