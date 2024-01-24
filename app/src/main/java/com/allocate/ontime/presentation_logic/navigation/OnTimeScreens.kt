package com.allocate.ontime.presentation_logic.navigation

sealed class OnTimeScreens(val name: String) {
    object SplashScreen : OnTimeScreens("Splash_Screen")
    object HomeScreen : OnTimeScreens("Home_Screen")
    object AdminScreen : OnTimeScreens("Admin_Screen")
    object SuperAdminScreen : OnTimeScreens("Super_Admin_Screen")
    object AdminRegistrationScreen : OnTimeScreens("Admin_Registration_Screen")
    object FobRegisterScreen : OnTimeScreens("Fob_Register_Screen")
    object SuperAdminSettingScreen : OnTimeScreens("Super_Admin_Setting_Screen")
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