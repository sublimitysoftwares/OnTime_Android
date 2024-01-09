package com.allocate.ontime.business_logic.viewmodel.super_admin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperAdminSettingViewModel @Inject constructor(private val repository: OnTimeRepository) :
    ViewModel() {
suspend fun getDeviceData()
        : DataOrException<DeviceInfo, Boolean, Exception> {
    return repository.getDeviceInfo()

}

}