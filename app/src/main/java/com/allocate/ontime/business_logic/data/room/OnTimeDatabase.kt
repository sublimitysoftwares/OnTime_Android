package com.allocate.ontime.business_logic.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.allocate.ontime.business_logic.utils.RoomConverters

@Database(entities = [DeviceInformation::class], version = 2, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class OnTimeDatabase: RoomDatabase() {
    abstract fun onTimeDatabaseDao(): OnTimeDatabaseDao
}