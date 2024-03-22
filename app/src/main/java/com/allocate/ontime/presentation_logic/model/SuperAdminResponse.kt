package com.allocate.ontime.presentation_logic.model

data class SuperAdminResponse(
    val ResponseCode: String,
    val ResponsePacket: ResponsePacket,
    val ResponseMessage: String,
    val IsSuccess: Boolean
)

data class ResponsePacket(
    val UserName: String,
    val Password: String
)