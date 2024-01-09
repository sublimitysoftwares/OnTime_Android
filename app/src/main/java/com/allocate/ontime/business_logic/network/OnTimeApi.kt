package com.allocate.ontime.business_logic.network

import com.allocate.ontime.presentation_logic.model.DeviceInfo
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface OnTimeApi {
    @GET(value = "GetDevice?IMEI=868646330255705")
    suspend fun getDeviceInfo(): DeviceInfo
}