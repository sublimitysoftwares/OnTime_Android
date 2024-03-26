package com.allocate.ontime.business_logic.viewmodel.super_admin

import android.annotation.SuppressLint
import android.content.Context
import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.business_logic.repository.DeviceInfoRepository
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
@SuppressLint("StaticFieldLeak")
class SuperAdminSettingViewModel @Inject
constructor(private val repository: DeviceInfoRepository,
            private val autoBackNavigationManager: AutoBackNavigationManager,
            @ApplicationContext private val context: Context) :
    ViewModel() {
    suspend fun getDeviceData()
            : DataOrException<DeviceInfo, Exception> {
        return repository.getDeviceInfo(context)
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