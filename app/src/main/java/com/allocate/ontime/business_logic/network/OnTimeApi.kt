package com.allocate.ontime.business_logic.network

import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton


@Singleton
interface OnTimeApi {

    // GET api to get device information through IMEI number of device.
    @GET(value = "GetDevice?IMEI=868646330255705")
    suspend fun getDeviceInfo(): DeviceInfo


    // POST api to edit the app related fields in the GET api.
    @POST("editDeviceAppData")
    suspend fun postEditDeviceInfo(
      @Body appInfo:AppInfo
    ):Response<AppInfo>
}