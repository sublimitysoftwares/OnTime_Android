package com.allocate.ontime.presentation_logic.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allocate.ontime.presentation_logic.screens.super_admin.AdminRegistrationScreen
import com.allocate.ontime.presentation_logic.screens.admin.AdminScreen
import com.allocate.ontime.presentation_logic.screens.super_admin.FobRegisterScreen
import com.allocate.ontime.presentation_logic.screens.home.HomeScreen
import com.allocate.ontime.presentation_logic.screens.super_admin.SuperAdminScreen
import com.allocate.ontime.presentation_logic.screens.super_admin.SuperAdminSettingScreen
import com.allocate.ontime.business_logic.viewmodel.super_admin.SuperAdminSettingViewModel


@ExperimentalComposeUiApi
@Composable
fun OnTimeNavigation(viewModel: SuperAdminSettingViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnTimeScreens.HomeScreen.name
    ) {
        composable(OnTimeScreens.HomeScreen.name) {
            HomeScreen(
                homeScreenRoot = {
                    when (it) {
                        HomeScreenRoot.AdminScreen -> navController.navigate(OnTimeScreens.AdminScreen.name)
                        HomeScreenRoot.SuperAdminScreen -> navController.navigate(OnTimeScreens.SuperAdminScreen.name)
                    }
                })
        }
        composable(OnTimeScreens.AdminScreen.name) {
            AdminScreen(navController = navController)
        }
        composable(OnTimeScreens.SuperAdminScreen.name) {
            SuperAdminScreen(
                superAdminScreenRoot = {
                    when (it) {
                        SuperAdminScreenRoot.AdminRegistrationScreen -> navController.navigate(
                            OnTimeScreens.AdminRegistrationScreen.name
                        )
                        SuperAdminScreenRoot.FobRegisterScreen -> navController.navigate(
                            OnTimeScreens.FobRegisterScreen.name
                        )
                        SuperAdminScreenRoot.SuperAdminSettingScreen -> navController.navigate(
                            OnTimeScreens.SuperAdminSettingScreen.name
                        )

                    }
                })
        }
        composable(OnTimeScreens.AdminRegistrationScreen.name) {
            AdminRegistrationScreen(navController = navController)
        }
        composable(OnTimeScreens.FobRegisterScreen.name) {
            FobRegisterScreen(navController = navController)
        }
        composable(OnTimeScreens.SuperAdminSettingScreen.name) {
            SuperAdminSettingScreen(navController = navController, viewModel)
        }
    }
}