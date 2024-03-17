package com.allocate.ontime.business_logic.viewmodel.admin


import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    autoBackNavigationManager: AutoBackNavigationManager,
) :
    ViewModel() {
        val navigationFlows = autoBackNavigationManager.navigationFlow

    init {
        startInteraction(autoBackNavigationManager)
    }

    fun startInteraction(autoBackNavigationManager: AutoBackNavigationManager){
        autoBackNavigationManager.addOrUpdateScreens(OnTimeScreens.AdminScreen.name)
    }
}