package com.allocate.ontime.presentation_logic.navigation

enum class OnTimeScreens {
    HomeScreen,
    AdminScreen,
    SuperAdminScreen,
    AdminRegistrationScreen,
    FobRegisterScreen,
    SuperAdminSettingScreen
}

sealed class HomeScreenRoot {
    object AdminScreen:HomeScreenRoot()
    object SuperAdminScreen:HomeScreenRoot()

}

sealed class SuperAdminScreenRoot {
    object AdminRegistrationScreen:SuperAdminScreenRoot()
    object FobRegisterScreen:SuperAdminScreenRoot()
    object SuperAdminSettingScreen:SuperAdminScreenRoot()
}