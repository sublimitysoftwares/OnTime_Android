package com.example.ontime_jitendra.model

data class DeviceInfo(
    val length: Int,
    val responsePacket: List<ResponsePacket>,
    val statusCode: Int
)