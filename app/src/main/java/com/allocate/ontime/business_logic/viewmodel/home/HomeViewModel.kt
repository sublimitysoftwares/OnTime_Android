package com.allocate.ontime.business_logic.viewmodel.home

import com.allocate.ontime.business_logic.autoback_navigation_manager.AutoBackNavigationManager
import androidx.lifecycle.ViewModel
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(autoBackNavigationManager: AutoBackNavigationManager) :
    ViewModel() {
    init {
//        autoBackNavigationManager.addScreens(OnTimeScreens.HomeScreen.name)
    }
}