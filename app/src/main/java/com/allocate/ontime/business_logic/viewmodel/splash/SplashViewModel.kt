package com.allocate.ontime.business_logic.viewmodel.splash

import android.util.Log
import android.util.MutableBoolean
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: OnTimeRepository,
    private val daoRepository: DaoRepository,
) :
    ViewModel() {

    private val _roomDataList = MutableStateFlow<List<DeviceInformation>>(emptyList())
    val roomDataList = _roomDataList.asStateFlow()

    private val _postApiDataList = MutableStateFlow<List<DeviceInfo>>(emptyList())
    val postApiDataList = _postApiDataList.asStateFlow()





    init {
        viewModelScope.launch(Dispatchers.IO) {
            daoRepository.getAllDeviceInfo().distinctUntilChanged().collect() { listOfDeviceInfo ->
                if (listOfDeviceInfo.isEmpty()) {
                    Log.d("Empty", "Empty List: ")
                } else {
                    _roomDataList.value = listOfDeviceInfo
                }
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