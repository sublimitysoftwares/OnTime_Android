package com.allocate.ontime.business_logic.network

import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.presentation_logic.model.ResponsePacket
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface OnTimeApi {
    @GET(value = "GetDevice?IMEI=868307030059158")
    suspend fun getDeviceInfo(): DeviceInfo
}