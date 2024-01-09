package com.allocate.ontime.business_logic.viewmodel.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.repository.DaoRepository
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: OnTimeRepository,private val daoRepository: DaoRepository) :
    ViewModel() {

    private val _apiDataList = MutableStateFlow<List<DeviceInformation>>(emptyList())
    val apiDataList = _apiDataList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            daoRepository.getAllDeviceInfo().distinctUntilChanged().collect(){listOfDeviceInfo->
                if (listOfDeviceInfo.isEmpty()){
                    Log.d("Empty", "Empty List: ")
                }else{
                    _apiDataList.value = listOfDeviceInfo
                }

            }

       Log.d("abc","$apiDataList")

        }

    }

    suspend fun getDeviceData()
            : DataOrException<DeviceInfo, Boolean, Exception> {
        return repository.getDeviceInfo()
    }

    fun addDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch { daoRepository.addDeviceInfo(deviceInformation) }

    fun updateDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch { daoRepository.updateDeviceInfo(deviceInformation) }

    fun deleteDeviceInfo(deviceInformation: DeviceInformation) =
        viewModelScope.launch { daoRepository.deleteDeviceInfo(deviceInformation) }


}