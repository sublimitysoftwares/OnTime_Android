package com.allocate.ontime.business_logic.viewmodel.super_admin

import android.util.Log
import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FobRegisterViewModel @Inject constructor(autoBackNavigationManager: AutoBackNavigationManager) :
    ViewModel() {
    val navigationFlow = autoBackNavigationManager.navigationFlow
    init {
        startInteraction(autoBackNavigationManager)
    }
    fun startInteraction(autoBackNavigationManager: AutoBackNavigationManager){
        autoBackNavigationManager.addOrUpdateScreens(OnTimeScreens.FobRegisterScreen.name)
    }
}