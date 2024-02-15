package com.allocate.ontime.business_logic.viewmodel.splash


import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.BuildConfig
import com.allocate.ontime.business_logic.annotations.DeviceInfoRetrofit
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import com.allocate.ontime.business_logic.utils.Utils
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
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
@DeviceInfoRetrofit
class SplashViewModel @Inject constructor(
    private val repository: DeviceInfoRepository,
    private val daoRepository: DaoRepository,
    context: Context,
) : ViewModel() {

    private val _acknowledgementStatus = MutableStateFlow(0)
    val acknowledgementStatus = _acknowledgementStatus.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val deviceInfoApiData = async { repository.getDeviceInfo() }.await()
            Log.d("data1","hello:$deviceInfoApiData")

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
                val asApiURL = data.ASApiURL
              Utils.asApiURL = asApiURL
            }
            observeAllDeviceInfo(this, deviceInfoApiData,context)
        }
    }

    private suspend fun observeAllDeviceInfo(
        coroutineScope: CoroutineScope,
        getDeviceInfoApiData: DataOrException<DeviceInfo, Exception>,
        context: Context
    ) {
        daoRepository.getAllDeviceInfo().distinctUntilChanged().collect() { listOfDeviceInfo ->
            if (listOfDeviceInfo.isEmpty()) {
                Log.d("Empty", "Empty List")
            } else {
                if (acknowledgementStatus.value == 0) {
                    coroutineScope.async {
                        getDeviceInfoApiData.data?.let {
                            val result = repository.postDeviceInfo(
                                appInfo = AppInfo(
                                    id = it.responsePacket.first()._id,
                                    macAddress = getMacAddress(context),
                                    app = "",
                                    appVersion = BuildConfig.VERSION_NAME
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
                                    Log.d("EXC", "$it")
                                }
                            }
                        } ?: run {
                            getDeviceInfoApiData.e?.let {
                                Log.d("EXC", "$it")
                            }
                        }
                    }.await()
                }
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun getMacAddress(context: Context): String {
        val manager = context.getSystemService(WIFI_SERVICE) as WifiManager
        val info = manager.connectionInfo
        return info.macAddress.uppercase(Locale.ROOT)
    }

    private suspend fun addDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch(Dispatchers.IO) { daoRepository.addDeviceInfo(deviceInformation) }

    private suspend fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch(Dispatchers.IO) { daoRepository.updateDeviceInfo(deviceInformation) }
}
