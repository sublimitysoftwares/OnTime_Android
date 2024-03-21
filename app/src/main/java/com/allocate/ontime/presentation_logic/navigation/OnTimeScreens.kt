package com.allocate.ontime.presentation_logic.navigation

import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.utils.ScreenRoutes

sealed class OnTimeScreens(val name: String) {
    object SplashScreen : OnTimeScreens(Constants.SplashScreen)
    object HomeScreen : OnTimeScreens(Constants.HomeScreen)
    object AdminScreen : OnTimeScreens(Constants.AdminScreen)
    object SuperAdminScreen : OnTimeScreens(Constants.SuperAdminScreen)
    object AdminRegistrationScreen : OnTimeScreens(Constants.AdminRegistrationScreen)
    object VisitorRegistrationScreen : OnTimeScreens(Constants.VisitorRegistrationScreen)
    object FobRegisterScreen : OnTimeScreens(Constants.FobRegisterScreen)
    object SuperAdminSettingScreen : OnTimeScreens(Constants.SuperAdminSettingScreen)
}

sealed class HomeScreenRoot {
    object AdminScreen : HomeScreenRoot()
    object SuperAdminScreen : HomeScreenRoot()
    object HomeScreen : HomeScreenRoot()
}

sealed class SuperAdminScreenRoot {
    object HomeScreen : SuperAdminScreenRoot()
    object AdminRegistrationScreen : SuperAdminScreenRoot()
    object VisitorRegistrationScreen : SuperAdminScreenRoot()
    object FobRegisterScreen : SuperAdminScreenRoot()
    object SuperAdminSettingScreen : SuperAdminScreenRoot()
    object SuperAdminScreen : SuperAdminScreenRoot()
}