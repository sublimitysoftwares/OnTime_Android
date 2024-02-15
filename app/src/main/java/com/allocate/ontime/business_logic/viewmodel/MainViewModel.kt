package com.allocate.ontime.business_logic.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allocate.ontime.business_logic.annotations.SuperAdminRetrofit
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(@SuperAdminRetrofit private val repository: DeviceInfoRepository): ViewModel(){

    init {
        viewModelScope.launch {
            val result = repository.postSuperAdminDetails()
            Log.d("MainViewModel","$result")
        }
    }
}