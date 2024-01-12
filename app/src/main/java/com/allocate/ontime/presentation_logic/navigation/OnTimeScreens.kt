package com.allocate.ontime.presentation_logic.navigation

sealed class OnTimeScreens {
    object SplashScreen
    object HomeScreen
    object AdminScreen
    object SuperAdminScreen
    object AdminRegistrationScreen
    object FobRegisterScreen
    object SuperAdminSettingScreen
}

sealed class HomeScreenRoot {
    object AdminScreen : HomeScreenRoot()
    object SuperAdminScreen : HomeScreenRoot()
}

sealed class SuperAdminScreenRoot {
    object HomeScreen : SuperAdminScreenRoot()
    object AdminRegistrationScreen : SuperAdminScreenRoot()
    object FobRegisterScreen : SuperAdminScreenRoot()
    object SuperAdminSettingScreen : SuperAdminScreenRoot()
}