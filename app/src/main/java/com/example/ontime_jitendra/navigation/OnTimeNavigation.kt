package com.example.ontime_jitendra.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ontime_jitendra.screens.AdminScreen
import com.example.ontime_jitendra.screens.HomeScreen

@ExperimentalComposeUiApi
@Composable
fun OnTimeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = OnTimeScreens.HomeScreen.name ) {
        composable(OnTimeScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(OnTimeScreens.AdminScreen.name){
            AdminScreen(navController = navController)
        }
    }
}