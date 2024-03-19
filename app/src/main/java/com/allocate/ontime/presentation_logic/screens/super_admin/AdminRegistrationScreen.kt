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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.viewmodel.super_admin.AdminRegistrationViewModel
import com.allocate.ontime.presentation_logic.navigation.SuperAdminScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens
import com.allocate.ontime.presentation_logic.widgets.InputField

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AdminRegistrationScreen(
    backToSuperAdminScreen: (SuperAdminScreenRoot) -> Unit,
    adminRegistrationViewModel: AdminRegistrationViewModel = hiltViewModel(),
) {

    val searchState = remember {
        mutableStateOf("")
    }

    val checkBoxState = remember {
        mutableStateOf(false)
    }

    val hasNoUserInteractionAdminRegistrationScreen =
        adminRegistrationViewModel.navigationFlow.collectAsState()
    Log.d(
        "AutoBackNavigationManager",
        "adminRegistration: ${hasNoUserInteractionAdminRegistrationScreen.value}"
    )

    if (hasNoUserInteractionAdminRegistrationScreen.value) {
        backToSuperAdminScreen(SuperAdminScreenRoot.SuperAdminScreen)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        adminRegistrationViewModel.startInteraction()
                    }
                )

            },
        color = Color.DarkGray.copy(alpha = MaterialTheme.dimens.surfaceColorAlphaValue)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.adminRegistrationScreenColumnTopPadding)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.ADMIN_PAGE),
                style = MaterialTheme.typography.headlineMedium,
                color = OnTimeColors.GREEN_HAZE,
                fontWeight = FontWeight.Bold
            )
            InputField(
                valueState = searchState,
                labelId = stringResource(id = R.string.Search_Employee),
                isSingleLine = true,
                modifier = Modifier
                    .size(
                        width = MaterialTheme.dimens.adminRegistrationScreenSearchTextFieldWidth,
                        height = MaterialTheme.dimens.adminRegistrationScreenSearchTextFieldHeight
                    )
                    .align(alignment = Alignment.End)
                    .padding(end = MaterialTheme.dimens.adminRegistrationScreenSearchTextFieldEndPadding),
                textStyle = MaterialTheme.typography.titleMedium,
            )
            Spacer(
                modifier = Modifier
                    .height(MaterialTheme.dimens.spacerHeight10)
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.dimens.adminRegistrationScreenSurfaceRowStartPadding,
                        end = MaterialTheme.dimens.adminRegistrationScreenSurfaceRowEndPadding
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(
                        text = stringResource(id = R.string.New_or_Edit_User_Registration),
                        color = OnTimeColors.White
                    )
                    Surface(
                        modifier = Modifier
                            .size(
                                width = MaterialTheme.dimens.surfaceWidth,
                                height = MaterialTheme.dimens.surfaceHeight
                            )
                            .padding(top = MaterialTheme.dimens.adminRegistrationScreenSurfaceTopPadding),
                        color = OnTimeColors.PORT_GORE
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            InputField(
                                valueState = searchState,
                                labelId = stringResource(id = R.string.First_Name),
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.userInputTextFieldHeight
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )
                            InputField(
                                valueState = searchState,
                                labelId = stringResource(id = R.string.Last_Name),
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.userInputTextFieldHeight
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )
                            InputField(
                                valueState = searchState,
                                labelId = stringResource(id = R.string.Employee_Number),
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.userInputTextFieldHeight
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(
                        text = stringResource(id = R.string.Fingerprint_Registration),
                        color = OnTimeColors.White
                    )
                    Surface(
                        modifier = Modifier
                            .size(
                                width = MaterialTheme.dimens.surfaceWidth,
                                height = MaterialTheme.dimens.surfaceHeight
                            )
                            .padding(top = MaterialTheme.dimens.adminRegistrationScreenSurfaceTopPadding),
                        color = OnTimeColors.PORT_GORE
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Row(
                                modifier = Modifier.padding(start = MaterialTheme.dimens.adminRegistrationScreenFingerPrintRowStartPadding),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                InputField(
                                    valueState = searchState,
                                    labelId = stringResource(id = R.string.Fingerprint_One),
                                    isSingleLine = true,
                                    modifier = Modifier
                                        .size(
                                            width = MaterialTheme.dimens.fingerprintInputTextFieldWidth,
                                            height = MaterialTheme.dimens.fingerprintInputTextFieldHeight
                                        ),
                                    isTrailingIcon = true,
                                    textStyle = MaterialTheme.typography.titleMedium
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.fingerprint),
                                    contentDescription = stringResource(id = R.string.fingerprint_img),
                                    modifier = Modifier.aspectRatio(MaterialTheme.dimens.adminRegistrationScreenFingerprintImgAspectRatio),
                                )
                            }
                            Row(
                                modifier = Modifier.padding(start = MaterialTheme.dimens.adminRegistrationScreenFingerPrintRowStartPadding),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                InputField(
                                    valueState = searchState,
                                    labelId = stringResource(id = R.string.Fingerprint_Two),
                                    isSingleLine = true,
                                    modifier = Modifier
                                        .size(
                                            width = MaterialTheme.dimens.fingerprintInputTextFieldWidth,
                                            height = MaterialTheme.dimens.fingerprintInputTextFieldHeight
                                        ),
                                    isTrailingIcon = true,
                                    textStyle = MaterialTheme.typography.titleMedium
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.fingerprint),
                                    contentDescription = stringResource(id = R.string.fingerprint_img),
                                    modifier = Modifier.aspectRatio(MaterialTheme.dimens.adminRegistrationScreenFingerprintImgAspectRatio),
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = MaterialTheme.dimens.adminRegistrationScreenFingerPrintRowStartPadding)
                            ) {
                                Checkbox(
                                    checked = false,
                                    onCheckedChange = { checkBoxState.value },
                                    colors = CheckboxDefaults.colors(uncheckedColor = OnTimeColors.White)
                                )
                                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth5))
                                Text(
                                    text = stringResource(id = R.string.Skip_Fingerprint),
                                    color = OnTimeColors.White
                                )
                            }
                        }
                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .weight(0.5f)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ontime_qr_code),
                    contentDescription = stringResource(id = R.string.qr_code_img),
                    modifier = Modifier.size(MaterialTheme.dimens.qrCodeLogoSize)
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(MaterialTheme.dimens.adminRegistrationScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(containerColor = OnTimeColors.GREEN_HAZE),
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.nextBtnWidth,
                        height = MaterialTheme.dimens.nextBtnHeight
                    )
                ) {
                    Text(text = stringResource(id = R.string.NEXT))
                }
            }
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight10))
            Text(
                text = stringResource(id = R.string.Please_scan_QR_Code_for_instructional_video_on_Finger_Scan_registrations),
                color = OnTimeColors.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.dimens.adminRegistrationScreenTextStartPadding),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(
                modifier = Modifier
                    .height(MaterialTheme.dimens.spacerHeight10)
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.dimens.adminScreenBottomRowBottomPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        backToSuperAdminScreen(SuperAdminScreenRoot.SuperAdminScreen)
                    },
                    shape = RoundedCornerShape(MaterialTheme.dimens.adminRegistrationScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OnTimeColors.GREEN_HAZE, contentColor = OnTimeColors.White
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
                    shape = RoundedCornerShape(MaterialTheme.dimens.adminRegistrationScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = OnTimeColors.GREEN_HAZE, contentColor = OnTimeColors.White
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
                            colorFilter = ColorFilter.tint(OnTimeColors.MANTIS),
                            modifier = Modifier.size(MaterialTheme.dimens.circleGreenImgSize)
                        )
                        Text(text = stringResource(id = R.string.View_Employee_Online))
                    }
                }
            }
        }
    }
}