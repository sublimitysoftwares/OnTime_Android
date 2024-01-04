package com.allocate.ontime.business_logic.repository

import android.util.Log
import androidx.compose.material3.AlertDialogDefaults
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.network.OnTimeApi
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