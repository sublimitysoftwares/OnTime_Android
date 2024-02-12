package com.allocate.ontime.business_logic.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit

@DeviceInfoRetrofit
@Database(entities = [DeviceInformation::class], version = 1, exportSchema = false)
abstract class OnTimeDatabase: RoomDatabase() {
    abstract fun deviceInfoDao(): DeviceInfoDao
}