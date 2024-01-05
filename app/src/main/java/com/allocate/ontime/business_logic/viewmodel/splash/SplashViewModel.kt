package com.allocate.ontime.business_logic.viewmodel.splash

import androidx.lifecycle.ViewModel
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: OnTimeRepository) :
    ViewModel() {
    suspend fun getDeviceData()
            : DataOrException<DeviceInfo, Boolean, Exception> {
        return repository.getDeviceInfo()

    }

}