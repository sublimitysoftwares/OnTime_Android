package com.allocate.ontime.business_logic.viewmodel.super_admin

import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.repository.OnTimeRepository
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SuperAdminSettingViewModel @Inject constructor(
    private val repository: OnTimeRepository,
    private val autoBackNavigationManager: AutoBackNavigationManager
) :
    ViewModel() {
    suspend fun getDeviceData()
            : DataOrException<DeviceInfo, Exception> {
        return repository.getDeviceInfo()
    }

    val navigationFlow = autoBackNavigationManager.navigationFlow

    init {
        startInteraction()
    }

    fun startInteraction() {
        autoBackNavigationManager.addOrUpdateScreens(OnTimeScreens.SuperAdminSettingScreen.name)
    }

    fun resetAutoBack() {
        autoBackNavigationManager.removeScreens(OnTimeScreens.SuperAdminSettingScreen.name)
    }
}