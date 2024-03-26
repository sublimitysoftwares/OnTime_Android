package com.allocate.ontime.business_logic.viewmodel.super_admin

import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminRegistrationViewModel @Inject constructor(private val autoBackNavigationManager: AutoBackNavigationManager) :
    ViewModel() {
    val navigationFlow = autoBackNavigationManager.navigationFlow

    init {
        startInteraction()
    }

    fun startInteraction() {
        autoBackNavigationManager.addOrUpdateScreens(OnTimeScreens.AdminRegistrationScreen.name)
    }

    fun resetAutoBack() {
        autoBackNavigationManager.removeScreens(OnTimeScreens.AdminRegistrationScreen.name)
    }
}