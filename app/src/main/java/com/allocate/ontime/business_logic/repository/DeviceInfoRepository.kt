package com.allocate.ontime.business_logic.repository

import android.util.Log
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.network.DeviceInfoApi
import com.allocate.ontime.business_logic.network.SuperAdminApi
import com.allocate.ontime.business_logic.utils.Utils
import com.allocate.ontime.encryption.EDModel
import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.EditDeviceInfo
import retrofit2.Response
import javax.inject.Inject


class DeviceInfoRepository @Inject constructor(
    private val deviceInfoApi: DeviceInfoApi,
    private val superAdminApi: SuperAdminApi
) {
    suspend fun getDeviceInfo(): DataOrException<DeviceInfo, Exception> {
        val dataOrException = DataOrException<DeviceInfo, Exception>()
        try {
            dataOrException.data = deviceInfoApi.getDeviceInfo(imei = Utils.imei)
//            dataOrException.data = deviceInfoApi.getDeviceInfo(imei = "867291070025769")
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
            dataOrException.data = deviceInfoApi.postEditDeviceInfo(appInfo)
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getAllDeviceInfo: ${dataOrException.e}")
        }
        return dataOrException
    }

    suspend fun postSuperAdminDetails(): DataOrException<Response<EDModel>, Exception> {
        val dataOrException = DataOrException<Response<EDModel>, Exception>()
        try {
            dataOrException.data = superAdminApi.getCISuperAdminDetails()
            Log.d("SuperAdmin", "${dataOrException.data}")
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("EXC", "getSuperAdminDetails: ${dataOrException.e}")
        }
        return dataOrException
    }
}