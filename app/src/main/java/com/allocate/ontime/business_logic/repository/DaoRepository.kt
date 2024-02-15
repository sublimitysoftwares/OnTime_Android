package com.allocate.ontime.business_logic.repository


import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
import com.allocate.ontime.business_logic.data.room.DeviceInfoDao
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@DeviceInfoRetrofit
class DaoRepository @Inject constructor(private val deviceInfoDao: DeviceInfoDao) {
    suspend fun addDeviceInfo(deviceInformation: DeviceInformation) =
        deviceInfoDao.insert(deviceInformation)

    suspend fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        deviceInfoDao.update(deviceInformation)

    fun getAllDeviceInfo(): kotlinx.coroutines.flow.Flow<List<DeviceInformation>> =
        deviceInfoDao.getDeviceInfo().flowOn(Dispatchers.IO).conflate()
}