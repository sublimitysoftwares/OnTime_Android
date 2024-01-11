package com.allocate.ontime.business_logic.repository


import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.data.room.OnTimeDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DaoRepository @Inject constructor(private val onTimeDatabaseDao: OnTimeDatabaseDao) {
    suspend fun addDeviceInfo(deviceInformation: DeviceInformation) =
        onTimeDatabaseDao.insert(deviceInformation)

    suspend fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        onTimeDatabaseDao.update(deviceInformation)


    suspend fun deleteDeviceInfo(deviceInformation: DeviceInformation) =
        onTimeDatabaseDao.delete(deviceInformation)

        fun getAllDeviceInfo(): kotlinx.coroutines.flow.Flow<List<DeviceInformation>> =
        onTimeDatabaseDao.getDeviceInfo().flowOn(Dispatchers.IO).conflate()



}