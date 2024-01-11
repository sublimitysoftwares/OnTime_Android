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
import com.allocate.ontime.presentation_logic.screens.splash.SplashScreen


@ExperimentalComposeUiApi
@Composable
fun OnTimeNavigation(viewModel: SuperAdminSettingViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnTimeScreens.SplashScreen.toString()
    ) {
        composable(OnTimeScreens.HomeScreen.toString()) {
            HomeScreen(
                homeScreenRoot = {
                    when (it) {
                        HomeScreenRoot.AdminScreen -> navController.navigate(OnTimeScreens.AdminScreen.toString())
                        HomeScreenRoot.SuperAdminScreen -> navController.navigate(OnTimeScreens.SuperAdminScreen.toString())
                    }
                })
        }
        composable(OnTimeScreens.SuperAdminScreen.toString()) {
            SuperAdminScreen(
                superAdminScreenRoot = {
                    when (it) {
                        SuperAdminScreenRoot.AdminRegistrationScreen -> navController.navigate(
                            OnTimeScreens.AdminRegistrationScreen.toString()
                        )
                        SuperAdminScreenRoot.FobRegisterScreen -> navController.navigate(
                            OnTimeScreens.FobRegisterScreen.toString()
                        )
                        SuperAdminScreenRoot.SuperAdminSettingScreen -> navController.navigate(
                            OnTimeScreens.SuperAdminSettingScreen.toString()
                        )
                        SuperAdminScreenRoot.HomeScreen -> navController.navigate(
                            OnTimeScreens.HomeScreen.toString()
                        )
                    }
                })
        }
        composable(OnTimeScreens.AdminScreen.toString()) {
            AdminScreen(navController = navController)
        }
        composable(OnTimeScreens.AdminRegistrationScreen.toString()) {
            AdminRegistrationScreen(navController = navController)
        }
        composable(OnTimeScreens.FobRegisterScreen.toString()) {
            FobRegisterScreen(navController = navController)
        }
        composable(OnTimeScreens.SuperAdminSettingScreen.toString()) {
            SuperAdminSettingScreen(navController = navController, viewModel)
        }
        composable(OnTimeScreens.SplashScreen.toString()) {
            SplashScreen(navController = navController)
        }
    }
}