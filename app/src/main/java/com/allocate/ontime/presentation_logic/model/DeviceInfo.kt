package com.allocate.ontime.presentation_logic.model

data class DeviceInfo(
    val length: Int,
    val responsePacket: List<DeviceInfoResponsePacket>,
    val statusCode: Int
)

