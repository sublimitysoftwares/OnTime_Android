package com.example.ontime_jitendra.navigation

import WindowInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ontime_jitendra.screens.AdminRegistrationScreen
import com.example.ontime_jitendra.screens.AdminScreen
import com.example.ontime_jitendra.screens.FobRegisterScreen
import com.example.ontime_jitendra.screens.HomeScreen
import com.example.ontime_jitendra.screens.SuperAdminScreen
import com.example.ontime_jitendra.screens.SuperAdminSettingScreen

@ExperimentalComposeUiApi
@Composable
fun OnTimeNavigation(windowInfo: WindowInfo) {
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
            SuperAdminSettingScreen(navController = navController)
        }
    }
}