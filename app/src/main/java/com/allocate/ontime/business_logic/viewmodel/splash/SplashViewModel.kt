package com.allocate.ontime.business_logic.viewmodel.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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


    private val _acknowledgementStatus = MutableStateFlow(0)
    val acknowledgementStatus = _acknowledgementStatus.asStateFlow()
    private val tag = "EXCEPTION"

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val deviceInfoApiData = async { repository.getDeviceInfo() }.await()

            deviceInfoApiData.data?.responsePacket?.first()?.let { data ->
                addDeviceInfo(
                    deviceInformation = DeviceInformation(
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
                        appVersion = data.AppVersion,
                        locationCode = data.LocationCode,
                        acknowledgementStatus = 0,
                        sites = data.Sites,
                        siteName = data.SiteName,
                        isRLD = data.IsRLD
                    )
                )
            }
            observeAllDeviceInfo(this, deviceInfoApiData)
        }
    }

    private suspend fun observeAllDeviceInfo(
        coroutineScope: CoroutineScope,
        getDeviceInfoApiData: DataOrException<DeviceInfo, Exception>,
    ) {
        daoRepository.getAllDeviceInfo().distinctUntilChanged().collect { listOfDeviceInfo ->
            if (listOfDeviceInfo.isEmpty()) {
                Log.d(tag, "Empty List")
            } else {
                if (acknowledgementStatus.value == 0) {
                    coroutineScope.async {
                        getDeviceInfoApiData.data?.let {
                            val result = repository.postDeviceInfo(
                                appInfo = AppInfo(
                                    id = it.responsePacket.first()._id,
                                    macAddress = "test9",
                                    app = "test9",
                                    appVersion = "test9"
                                )
                            )
                            if (result.data?.ResponseCode == 201) {
                                _acknowledgementStatus.value = 1
                                getDeviceInfoApiData.data?.responsePacket?.first()?.let { data ->
                                    updateDeviceInfo(
                                        deviceInformation = DeviceInformation(
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
                                            appVersion = data.AppVersion,
                                            locationCode = data.LocationCode,
                                            acknowledgementStatus = acknowledgementStatus.value,
                                            sites = data.Sites,
                                            siteName = data.SiteName,
                                            isRLD = data.IsRLD
                                        )
                                    )
                                }
                            } else {
                                result.e?.let {
                                    Log.d(tag, "$it")
                                }
                            }
                        } ?: run {
                            getDeviceInfoApiData.e?.let {
                                Log.d(tag, "$it")
                            }
                        }
                    }.await()
                }
            }
        }
    }

    private suspend fun addDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch(Dispatchers.IO) { daoRepository.addDeviceInfo(deviceInformation) }

    private suspend fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch(Dispatchers.IO) { daoRepository.updateDeviceInfo(deviceInformation) }
}
