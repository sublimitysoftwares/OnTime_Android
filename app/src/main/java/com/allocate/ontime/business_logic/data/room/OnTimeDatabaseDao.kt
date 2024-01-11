package com.allocate.ontime.business_logic.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface OnTimeDatabaseDao {

    @Query(value = "SELECT * from device_info")
    fun getDeviceInfo(): Flow<List<DeviceInformation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(deviceInfo: DeviceInformation)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(deviceInfo: DeviceInformation)


    @Delete
    suspend fun delete(deviceInfo: DeviceInformation)

}