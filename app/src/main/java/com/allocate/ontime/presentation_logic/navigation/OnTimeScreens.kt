package com.allocate.ontime.presentation_logic.navigation

import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.utils.ScreenRoutes

sealed class OnTimeScreens(val name: String) {
    object SplashScreen : OnTimeScreens(ScreenRoutes.SplashScreen)
    object HomeScreen : OnTimeScreens(ScreenRoutes.HomeScreen)
    object AdminScreen : OnTimeScreens(ScreenRoutes.AdminScreen)
    object SuperAdminScreen : OnTimeScreens(ScreenRoutes.SuperAdminScreen)
    object AdminRegistrationScreen : OnTimeScreens(ScreenRoutes.AdminRegistrationScreen)
    object FobRegisterScreen : OnTimeScreens(ScreenRoutes.FobRegisterScreen)
    object SuperAdminSettingScreen : OnTimeScreens(ScreenRoutes.SuperAdminSettingScreen)
}


sealed class HomeScreenRoot {
    object AdminScreen : HomeScreenRoot()
    object SuperAdminScreen : HomeScreenRoot()
    object HomeScreen : HomeScreenRoot()
}

sealed class SuperAdminScreenRoot {
    object HomeScreen : SuperAdminScreenRoot()
    object AdminRegistrationScreen : SuperAdminScreenRoot()
    object FobRegisterScreen : SuperAdminScreenRoot()
    object SuperAdminSettingScreen : SuperAdminScreenRoot()
    object SuperAdminScreen : SuperAdminScreenRoot()
}