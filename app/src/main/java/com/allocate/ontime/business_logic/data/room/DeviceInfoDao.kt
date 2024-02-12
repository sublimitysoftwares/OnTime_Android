package com.allocate.ontime.business_logic.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
import kotlinx.coroutines.flow.Flow

@DeviceInfoRetrofit
@Dao
interface DeviceInfoDao {
    @Query(value = "SELECT * from device_info")
    fun getDeviceInfo(): Flow<List<DeviceInformation>>
    @Upsert
    suspend fun insert(deviceInfo: DeviceInformation)
    @Update
    suspend fun update(deviceInfo: DeviceInformation)
}