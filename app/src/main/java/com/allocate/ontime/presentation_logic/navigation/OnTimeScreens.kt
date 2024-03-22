package com.allocate.ontime.presentation_logic.navigation

import com.allocate.ontime.business_logic.utils.Constants
import com.allocate.ontime.business_logic.utils.ScreenRoutes

sealed class OnTimeScreens(val name: String) {
    object SplashScreen : OnTimeScreens(Constants.SPLASH_SCREEN)
    object HomeScreen : OnTimeScreens(Constants.HOME_SCREEN)
    object AdminScreen : OnTimeScreens(Constants.ADMIN_SCREEN)
    object SuperAdminScreen : OnTimeScreens(Constants.SUPER_ADMIN_SCREEN)
    object AdminRegistrationScreen : OnTimeScreens(Constants.ADMIN_REGISTRATION_SCREEN)
    object FobRegisterScreen : OnTimeScreens(Constants.FOB_REGISTER_SCREEN)
    object SuperAdminSettingScreen : OnTimeScreens(Constants.SUPER_ADMIN_SETTING_SCREEN)
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