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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.allocate.ontime.business_logic.data.room.DeviceInformation
import com.allocate.ontime.business_logic.viewmodel.splash.SplashViewModel
import com.allocate.ontime.presentation_logic.model.AppInfo
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.presentation_logic.model.DeviceInfoResponsePacket
import com.allocate.ontime.presentation_logic.navigation.OnTimeScreens
import retrofit2.Response

@Composable
fun SplashScreen(navController: NavController, splashViewModel: SplashViewModel = hiltViewModel()) {
    val deviceInfoApiData = produceState<DataOrException<DeviceInfo, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = splashViewModel.getDeviceData()
    }.value

    val deviceInfoListsRoomData = splashViewModel.deviceInfoListRoomData.collectAsState().value

    val id = rememberSaveable {
        mutableStateOf("")
    }
    val deviceId = rememberSaveable {
        mutableLongStateOf(0)
    }
    val trustOrganization = rememberSaveable {
        mutableStateOf("")
    }
    val uniqueIdentifier = rememberSaveable {
        mutableStateOf("")
    }
    val location = rememberSaveable {
        mutableStateOf("")
    }
    val serialNumber = rememberSaveable {
        mutableStateOf("")
    }
    val latitude = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }
    val longitude = rememberSaveable {
        mutableDoubleStateOf(0.0)
    }
    val postcode = rememberSaveable {
        mutableStateOf("")
    }
    val status = rememberSaveable {
        mutableStateOf("")
    }
    val asInstance = rememberSaveable {
        mutableStateOf("")
    }
    val asEmployeeOnlineURL = rememberSaveable {
        mutableStateOf("")
    }
    val asApiURL = rememberSaveable {
        mutableStateOf("")
    }
    val appVersion = rememberSaveable {
        mutableStateOf("")
    }
    val locationCode = rememberSaveable {
        mutableStateOf("")
    }
    val acknowledgementStatus = rememberSaveable {
        mutableIntStateOf(0)
    }

    deviceInfoApiData.data?.let { rememberUpdatedState(it.responsePacket) }?.value?.forEach {
        Log.d("rahul", "$it")
        id.value = it._id
        deviceId.longValue = it.DeviceId.toLong()
        trustOrganization.value = it.TrustOrganization
        uniqueIdentifier.value = it.Unique_Identifier
        location.value = it.Location
        serialNumber.value = it.SerialNumber
        latitude.doubleValue = it.Latitude.toDouble()
        longitude.doubleValue = it.Longitude.toDouble()
        postcode.value = it.Postcode
        status.value = it.Status
        asInstance.value = it.ASInstance
        asEmployeeOnlineURL.value = it.ASEmployeeOnlineURL
        asApiURL.value = it.ASApiURL
        appVersion.value = it.AppVersion.toString()
        locationCode.value = it.LocationCode
    }


    @Composable
    fun postEditDeviceDataCall(): DataOrException<Response<AppInfo>, Boolean, Exception> {
        return produceState<DataOrException<Response<AppInfo>, Boolean, Exception>>(
            initialValue = DataOrException(loading = false)
        ) {
            value = splashViewModel.postDeviceData(
                appInfo = AppInfo(
                    id = "658c1d97eb1ccaa54de6d5d3",
                    macAddress = "test3",
                    app = "test3",
                    appVersion = "test3"
                )
            )
        }.value
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
            if (deviceInfoApiData.loading == true) {
                CircularProgressIndicator(modifier = Modifier.size(80.dp))
                // if api response is not stored in room database then acknowledgementStatus
                // value should be 0.
                if (deviceInfoListsRoomData.isEmpty()) {
                    acknowledgementStatus.intValue = 0
                }
            } else {
                // if api response is stored in room database then acknowledgementStatus
                // value should be 1.
                if (deviceInfoListsRoomData.isNotEmpty()) {
                    acknowledgementStatus.intValue = 1
                }
                // we are inserting device information(coming from getDevice api through IMEI number) into room database.
                splashViewModel.addDeviceInfo(
                    deviceInformation = DeviceInformation(
                        id = id.value,
                        deviceId = deviceId.longValue,
                        trustOrganization = trustOrganization.value,
                        uniqueIdentifier = uniqueIdentifier.value,
                        location = location.value,
                        serialNumber = serialNumber.value,
                        latitude = latitude.doubleValue,
                        longitude = longitude.doubleValue,
                        postcode = postcode.value,
                        status = status.value,
                        asInstance = asInstance.value,
                        asEmployeeOnlineURL = asEmployeeOnlineURL.value,
                        asApiURL = asApiURL.value,
                        appVersion = appVersion.value,
                        locationCode = locationCode.value,
                        acknowledgementStatus = acknowledgementStatus.intValue
                    )
                )
                // we are updating the acknowledgementStatus value into room database.
                splashViewModel.updateDeviceInfo(
                    deviceInformation = DeviceInformation(
                        id = id.value,
                        deviceId = deviceId.longValue,
                        trustOrganization = trustOrganization.value,
                        uniqueIdentifier = uniqueIdentifier.value,
                        location = location.value,
                        serialNumber = serialNumber.value,
                        latitude = latitude.doubleValue,
                        longitude = longitude.doubleValue,
                        postcode = postcode.value,
                        status = status.value,
                        asInstance = asInstance.value,
                        asEmployeeOnlineURL = asEmployeeOnlineURL.value,
                        asApiURL = asApiURL.value,
                        appVersion = appVersion.value,
                        locationCode = locationCode.value,
                        acknowledgementStatus = acknowledgementStatus.intValue
                    )
                )
                // calling post api to edit the data in getDevice api and acknowledging that
                // we have completed the device setup.
                postEditDeviceDataCall()
                // navigating from splash screen to home screen.
                navController.navigate(OnTimeScreens.HomeScreen.toString())
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Please wait while device setup is completed...",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}