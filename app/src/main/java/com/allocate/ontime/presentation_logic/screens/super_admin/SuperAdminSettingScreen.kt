package com.allocate.ontime.presentation_logic.screens.super_admin

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.data.DataOrException
import com.allocate.ontime.business_logic.viewmodel.super_admin.SuperAdminSettingViewModel
import com.allocate.ontime.presentation_logic.model.DeviceInfo
import com.allocate.ontime.presentation_logic.navigation.SuperAdminScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens
import com.allocate.ontime.presentation_logic.widgets.InputField

@SuppressLint("SuspiciousIndentation")
@Composable
fun SuperAdminSettingScreen(
    backToSuperAdminScreen: (SuperAdminScreenRoot) -> Unit,
    superAdminViewModel: SuperAdminSettingViewModel = hiltViewModel(),
) {
    val checkBoxState = remember {
        mutableStateOf(false)
    }
    val trustState = remember {
        mutableStateOf("")
    }
    val locationState = remember {
        mutableStateOf("")
    }
    val postCodeState = remember {
        mutableStateOf("")
    }
    val uniqueIdentifierState = remember {
        mutableStateOf("")
    }
    val latitudeState = remember {
        mutableStateOf("")
    }
    val longitudeState = remember {
        mutableStateOf("")
    }
    val latLngState = remember {
        mutableStateOf("")
    }
    val siteNameState = remember {
        mutableStateOf("")
    }
    val isRLD = remember {
        mutableStateOf(false)
    }

    val deviceData = produceState<DataOrException<DeviceInfo, Exception>>(
        initialValue = DataOrException()
    ) {
        value = superAdminViewModel.getDeviceData()
    }.value

    if (deviceData.data?.statusCode == 200) {
        deviceData.data!!.responsePacket.forEach {
            trustState.value = it.TrustOrganization
            locationState.value = it.Location
            postCodeState.value = it.Postcode
            uniqueIdentifierState.value = it.Unique_Identifier
            latitudeState.value = it.Latitude
            longitudeState.value = it.Longitude
            siteNameState.value = it.SiteName
            isRLD.value = it.IsRLD
        }
        latLngState.value = latitudeState.value + ',' + longitudeState.value
    }

    val hasNoUserInteractionSuperAdminSettingScreen =
        superAdminViewModel.navigationFlow.collectAsState()

    if (hasNoUserInteractionSuperAdminSettingScreen.value) {
        backToSuperAdminScreen(SuperAdminScreenRoot.SuperAdminScreen)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        superAdminViewModel.startInteraction()
                    }
                )

            },
        color = Color.DarkGray.copy(alpha = MaterialTheme.dimens.surfaceColorAlphaValue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.Super_Admin_Setting),
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF85D32C),
                fontWeight = FontWeight.Bold
            )
            SuperAdminSettingInfo(
                trustState,
                locationState,
                postCodeState,
                uniqueIdentifierState,
                latLngState,
                siteNameState,
                isRLD
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.Clock_In_or_Out),
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Switch(
                    checked = true, onCheckedChange = {}, colors = SwitchDefaults.colors(
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray,
                        checkedThumbColor = Color.Cyan,
                        checkedTrackColor = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.Swipe_And_Go),
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { checkBoxState.value },
                    colors = CheckboxDefaults.colors(uncheckedColor = Color.White)
                )
                Text(
                    text = stringResource(id = R.string.Enable_two_way_Authentication),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.Enable_Pin),
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Switch(
                    checked = true, onCheckedChange = {}, colors = SwitchDefaults.colors(
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray,
                        checkedThumbColor = Color.Cyan,
                        checkedTrackColor = Color.White
                    )
                )
                Text(
                    text = stringResource(id = R.string.Disable_Pin),
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(MaterialTheme.dimens.superAdminSettingScreenButtonsCornerShapeSize),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),
            ) {
                Text(text = stringResource(id = R.string.CHECK_FOR_UPDATE))
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight10))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.dimens.superAdminSettingScreenBottomRowBottomPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        backToSuperAdminScreen(SuperAdminScreenRoot.SuperAdminScreen)
                    },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminSettingScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    border = BorderStroke(
                        width = MaterialTheme.dimens.superAdminSettingScreenButtonsBorderWidth,
                        color = Color.White
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_black),
                            contentDescription = stringResource(id = R.string.circle_black_img),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(MaterialTheme.dimens.circleBlackImgSize)
                        )
                        Text(text = stringResource(id = R.string.Click_here_to_go_back))
                    }
                }
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminSettingScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color(0xFF5B6F46)
                    ),
                    border = BorderStroke(
                        width = MaterialTheme.dimens.superAdminSettingScreenButtonsBorderWidth,
                        color = Color.White
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_green),
                            contentDescription = stringResource(id = R.string.circle_green_img),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(MaterialTheme.dimens.circleGreenImgSize)
                        )
                        Text(text = stringResource(id = R.string.View_Employee_Online))
                    }
                }
            }
        }
    }
}

@Composable
private fun SuperAdminSettingInfo(
    trustState: MutableState<String>,
    locationState: MutableState<String>,
    postCodeState: MutableState<String>,
    uniqueIdentifierState: MutableState<String>,
    latLngState: MutableState<String>,
    siteNameState: MutableState<String>,
    isRLD: MutableState<Boolean>
) {
    Surface(
        modifier = Modifier
            .size(
                width = MaterialTheme.dimens.superAdminSettingScreenSurfaceWidth,
                height = MaterialTheme.dimens.superAdminSettingScreenSurfaceHeight
            )
            .padding(top = MaterialTheme.dimens.superAdminSettingScreenSurfaceTopPadding),
        color = Color(0xFF5A5656)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = R.string.Trust_or_Organization),
                    color = Color.White,
                    modifier = Modifier.padding(start = MaterialTheme.dimens.superAdminSettingScreenColumnStartPadding)
                )
                InputField(
                    valueState = trustState,
                    labelId = "",
                    enabled = false,
                    isSingleLine = true,
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminSettingScreenTextFieldsWidth,
                        height = MaterialTheme.dimens.superAdminSettingScreenTextFieldsHeight
                    ),
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(id = R.string.Location_Do_not_add_semi_colon),
                    color = Color.White,
                    modifier = Modifier.padding(start = MaterialTheme.dimens.superAdminSettingScreenColumnStartPadding)
                )
                InputField(
                    valueState = locationState,
                    labelId = "",
                    enabled = false,
                    isSingleLine = true,
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminSettingScreenTextFieldsWidth,
                        height = MaterialTheme.dimens.superAdminSettingScreenTextFieldsHeight
                    ),
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(id = R.string.Postcode),
                    color = Color.White,
                    modifier = Modifier.padding(start = MaterialTheme.dimens.superAdminSettingScreenColumnStartPadding)
                )
                InputField(
                    valueState = postCodeState,
                    labelId = "",
                    enabled = false,
                    isSingleLine = true,
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminSettingScreenTextFieldsWidth,
                        height = MaterialTheme.dimens.superAdminSettingScreenTextFieldsHeight
                    ),
                    textStyle = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth10))
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = R.string.Unique_Identifier_only),
                    color = Color.White,
                    modifier = Modifier.padding(start = MaterialTheme.dimens.superAdminSettingScreenColumnStartPadding)
                )
                InputField(
                    valueState = uniqueIdentifierState,
                    labelId = "",
                    enabled = false,
                    isSingleLine = true,
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminSettingScreenTextFieldsWidth,
                        height = MaterialTheme.dimens.superAdminSettingScreenTextFieldsHeight
                    ),
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(id = R.string.LatLng),
                    color = Color.White,
                    modifier = Modifier.padding(start = MaterialTheme.dimens.superAdminSettingScreenColumnStartPadding)
                )
                InputField(
                    valueState = latLngState,
                    labelId = "",
                    enabled = false,
                    isSingleLine = true,
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminSettingScreenTextFieldsWidth,
                        height = MaterialTheme.dimens.superAdminSettingScreenTextFieldsHeight
                    ),
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Site Name",
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = MaterialTheme.dimens.superAdminSettingScreenColumnStartPadding)
                        .alpha(if (isRLD.value) 0f else 1f)
                )
                InputField(
                    valueState = siteNameState,
                    labelId = "",
                    enabled = false,
                    isSingleLine = true,
                    modifier = Modifier
                        .size(
                            width = MaterialTheme.dimens.superAdminSettingScreenTextFieldsWidth,
                            height = MaterialTheme.dimens.superAdminSettingScreenTextFieldsHeight
                        )
                        .alpha(if (isRLD.value) 0f else 1f),
                    textStyle = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}