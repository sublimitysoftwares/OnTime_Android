package com.allocate.ontime.business_logic.viewmodel.super_admin


import androidx.lifecycle.ViewModel
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuperAdminSettingViewModel @Inject
constructor(private val repository: DeviceInfoRepository) :
    ViewModel() {
    suspend fun getDeviceData()
            : DataOrException<DeviceInfo, Exception> {
        return repository.getDeviceInfo()
    }
}