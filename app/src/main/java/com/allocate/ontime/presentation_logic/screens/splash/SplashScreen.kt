package com.allocate.ontime.presentation_logic.screens.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.viewmodel.splash.SplashViewModel
import com.allocate.ontime.presentation_logic.navigation.HomeScreenRoot

@Composable
fun SplashScreen(
    homeScreenRoot: (HomeScreenRoot) -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val acknowledgementStatusFromDB = splashViewModel.acknowledgementStatus.collectAsState()
    if (acknowledgementStatusFromDB.value == 1){
        homeScreenRoot(HomeScreenRoot.HomeScreen)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.splash_ontime_logo),
                contentDescription =
                "splash_ontime_logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f),
                colorFilter = ColorFilter.tint(color = Color.Black)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Please wait while device setup is completed...",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}