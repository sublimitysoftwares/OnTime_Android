package com.allocate.ontime.business_logic.viewmodel.admin

import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val autoBackNavigationManager: AutoBackNavigationManager,
) :
    ViewModel() {
    val navigationFlows = autoBackNavigationManager.navigationFlow

    init {
        startInteraction()
    }

    fun startInteraction() {
        autoBackNavigationManager.addOrUpdateScreens(OnTimeScreens.AdminScreen.name)
    }
}