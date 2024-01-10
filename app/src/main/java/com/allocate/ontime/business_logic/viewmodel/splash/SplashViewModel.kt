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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: OnTimeRepository,
    private val daoRepository: DaoRepository
) :
    ViewModel() {

    private val _apiDataList = MutableStateFlow<List<DeviceInformation>>(emptyList())
    val apiDataList = _apiDataList.asStateFlow()

    private val _postApiDataList = MutableStateFlow<List<DeviceInfo>>(emptyList())
    val postApiDataList = _postApiDataList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            daoRepository.getAllDeviceInfo().distinctUntilChanged().collect() { listOfDeviceInfo ->
                if (listOfDeviceInfo.isEmpty()) {
                    Log.d("Empty", "Empty List: ")

                    // i have to apply acknowledgement api here and i have to change the
                    // acknowledgement status to 1.

                } else {
                    _apiDataList.value = listOfDeviceInfo
                    // i have to navigate to home screen here.
                }

                Log.d("abcde", "${apiDataList.value}")
            }


        }

    }

    suspend fun getDeviceData()
            : DataOrException<DeviceInfo, Boolean, Exception> {
        return repository.getDeviceInfo()
    }

    suspend fun postDeviceData(
       appInfo: AppInfo
    ) : DataOrException<Response<AppInfo>, Boolean, Exception> {
        return repository.postDeviceInfo(appInfo)
    }

    fun addDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch { daoRepository.addDeviceInfo(deviceInformation) }

    fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch { daoRepository.updateDeviceInfo(deviceInformation) }

    fun deleteDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch { daoRepository.deleteDeviceInfo(deviceInformation) }


}