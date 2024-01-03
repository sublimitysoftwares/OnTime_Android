package com.example.ontime_jitendra.network

import com.example.ontime_jitendra.model.DeviceInfo
import com.example.ontime_jitendra.model.ResponsePacket
import retrofit2.http.GET
import javax.inject.Singleton


@Singleton
interface OnTimeApi {
    @GET(value = "GetDevice?IMEI=868307030059158")
    suspend fun getDeviceInfo(): DeviceInfo
}