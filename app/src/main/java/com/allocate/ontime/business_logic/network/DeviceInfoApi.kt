package com.allocate.ontime.business_logic.network

import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.presentation_logic.model.EditDeviceInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DeviceInfoApi {

    @GET(value = "GetDevice")
    suspend fun getDeviceInfo(@Query("IMEI") imei: String?): DeviceInfo

    // POST api to edit the app related fields in the GET api.
    @POST(Constants.EDIT_DEVICE_INFO_URL_ENDPOINT)
    suspend fun postEditDeviceInfo(
        @Body appInfo: AppInfo
    ): EditDeviceInfo
}