package com.allocate.ontime.business_logic.utils

import androidx.room.TypeConverter
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.google.gson.Gson

class RoomConverters{
    @TypeConverter
    fun objectToJson(value: DeviceInfo?) = Gson().toJson(value)
}