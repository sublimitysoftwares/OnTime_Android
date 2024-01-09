package com.allocate.ontime.business_logic.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import javax.annotation.Nonnull


@Entity(tableName = "device_info")
data class DeviceInformation(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "api_data")
    val apiData: String,
    @ColumnInfo(name = "acknowledgement_status")
    val acknowledgementStatus: Int = 0
)