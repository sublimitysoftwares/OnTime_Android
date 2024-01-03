package com.allocate.ontime.presentation_logic.navigation

import WindowInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.allocate.ontime.presentation_logic.screens.AdminRegistrationScreen
import com.allocate.ontime.presentation_logic.screens.AdminScreen
import com.allocate.ontime.presentation_logic.screens.FobRegisterScreen
import com.allocate.ontime.presentation_logic.screens.HomeScreen
import com.allocate.ontime.presentation_logic.screens.SuperAdminScreen
import com.allocate.ontime.presentation_logic.screens.SuperAdminSettingScreen
import com.allocate.ontime.presentation_logic.screens.SuperAdminSettingViewModel

@ExperimentalComposeUiApi
@Composable
fun OnTimeNavigation(windowInfo: WindowInfo, viewModel: SuperAdminSettingViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = OnTimeScreens.HomeScreen.name ) {
        composable(OnTimeScreens.HomeScreen.name){
            HomeScreen(navController = navController,windowInfo)
        }
        composable(OnTimeScreens.AdminScreen.name){
            AdminScreen(navController = navController,windowInfo)
        }
        composable(OnTimeScreens.SuperAdminScreen.name){
            SuperAdminScreen(navController = navController,windowInfo)
        }
        composable(OnTimeScreens.AdminRegistrationScreen.name){
            AdminRegistrationScreen(navController = navController,windowInfo)
        }
        composable(OnTimeScreens.FobRegisterScreen.name){
            FobRegisterScreen(navController = navController)
        }
        composable(OnTimeScreens.SuperAdminSettingScreen.name){
            SuperAdminSettingScreen(navController = navController,viewModel)
        }
    }
}