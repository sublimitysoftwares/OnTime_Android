package com.allocate.ontime.business_logic.viewmodel.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.presentation_logic.model.AppInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: OnTimeRepository,
    private val daoRepository: DaoRepository,
) : ViewModel() {

    private val _deviceInfoListRoomData = MutableStateFlow<List<DeviceInformation>>(emptyList())
    val deviceInfoListRoomData = _deviceInfoListRoomData.asStateFlow()

    private val _acknowledgementStatus = MutableStateFlow(0)
    private val acknowledgementStatus = _acknowledgementStatus.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val getDeviceInfoApiData = async { repository.getDeviceInfo() }.await()

            getDeviceInfoApiData.data?.responsePacket?.first()?.let { data ->
                DeviceInformation(
                    id = data._id,
                    deviceId = data.DeviceId.toLong(),
                    trustOrganization = data.TrustOrganization,
                    uniqueIdentifier = data.Unique_Identifier,
                    location = data.Location,
                    serialNumber = data.SerialNumber,
                    latitude = data.Latitude.toDouble(),
                    longitude = data.Longitude.toDouble(),
                    postcode = data.Postcode,
                    status = data.Status,
                    asInstance = data.ASInstance,
                    asEmployeeOnlineURL = data.ASEmployeeOnlineURL,
                    asApiURL = data.ASApiURL,
                    appVersion = data.AppVersion!!,
                    locationCode = data.LocationCode,
                    acknowledgementStatus = acknowledgementStatus.value
                )
            }?.let {
                addDeviceInfo(
                    deviceInformation = it
                )
            }

            daoRepository.getAllDeviceInfo().distinctUntilChanged()
                .collect() { listOfDeviceInfo ->
                    if (listOfDeviceInfo.isEmpty()) {
                        Log.d("Empty", "Empty List: ")
                    } else {
                        _deviceInfoListRoomData.value = listOfDeviceInfo
                        deviceInfoListRoomData.value.forEach {
                            if (it.acknowledgementStatus == 0) {
                                val result = async {
                                    getDeviceInfoApiData.data?.let {
                                        repository.postDeviceInfo(
                                            appInfo = AppInfo(
                                                id = it.responsePacket.first()._id,
                                                macAddress = "test8",
                                                app = "test8",
                                                appVersion = "test8"
                                            )
                                        )
                                    } ?: run {
                                        getDeviceInfoApiData.e?.let {
                                            Log.d("EXC", "$it")
                                        }
                                    }
                                }.await()
                                result.run {
                                    _acknowledgementStatus.value = 1
                                    getDeviceInfoApiData.data?.responsePacket?.first()
                                        ?.let { data ->
                                            DeviceInformation(
                                                id = data._id,
                                                deviceId = data.DeviceId.toLong(),
                                                trustOrganization = data.TrustOrganization,
                                                uniqueIdentifier = data.Unique_Identifier,
                                                location = data.Location,
                                                serialNumber = data.SerialNumber,
                                                latitude = data.Latitude.toDouble(),
                                                longitude = data.Longitude.toDouble(),
                                                postcode = data.Postcode,
                                                status = data.Status,
                                                asInstance = data.ASInstance,
                                                asEmployeeOnlineURL = data.ASEmployeeOnlineURL,
                                                asApiURL = data.ASApiURL,
                                                appVersion = data.AppVersion!!,
                                                locationCode = data.LocationCode,
                                                acknowledgementStatus = acknowledgementStatus.value
                                            )
                                        }
                                }?.let {
                                    updateDeviceInfo(
                                        deviceInformation = it
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    private suspend fun addDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch(Dispatchers.IO) { daoRepository.addDeviceInfo(deviceInformation) }

    private suspend fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch(Dispatchers.IO) { daoRepository.updateDeviceInfo(deviceInformation) }
}
