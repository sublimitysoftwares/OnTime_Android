package com.allocate.ontime.presentation_logic.screens.splash


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.viewmodel.splash.SplashViewModel
import com.allocate.ontime.presentation_logic.model.DeviceInfo

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {
    val deviceData = produceState<DataOrException<DeviceInfo, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = splashViewModel.getDeviceData()
    }.value

    Log.d("deviceData_splash", "SuperAdminSettingScreen: $deviceData")




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
            if (deviceData.loading == true) {
                CircularProgressIndicator(modifier = Modifier.size(80.dp))

            } else {


            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Please wait while device setup is completed...",
                style = MaterialTheme.typography.headlineSmall,
            )

        }

    }


}