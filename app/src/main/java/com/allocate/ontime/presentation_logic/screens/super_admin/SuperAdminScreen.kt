package com.allocate.ontime.presentation_logic.screens.super_admin


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.utils.OnTimeColors
import com.allocate.ontime.presentation_logic.navigation.SuperAdminScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens

@Composable
fun SuperAdminScreen(superAdminScreenRoot: (SuperAdminScreenRoot) -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = OnTimeColors.TORY_BLUE
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.superAdminScreenColumnTopPadding)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.SUPER_ADMIN_PAGE),
                style = MaterialTheme.typography.headlineMedium,
                color = OnTimeColors.GREEN_HAZE,
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = OnTimeColors.GREEN_HAZE),
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .size(
                        width = MaterialTheme.dimens.switchToMobileModeBtnWidth,
                        height = MaterialTheme.dimens.switchToMobileModeBtnHeight
                    )
                    .padding(end = MaterialTheme.dimens.switchToMobileModeBtnEndPadding),
                shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize)
            ) {
                Text(text = stringResource(id = R.string.switch_to_mobile_mode))
            }
            Spacer(modifier = Modifier.weight(MaterialTheme.dimens.superAdminScreenSpacerWeightFraction))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.dimens.superAdminScreenRowStartPadding,
                        end = MaterialTheme.dimens.superAdminScreenRowEndPadding
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        superAdminScreenRoot(SuperAdminScreenRoot.AdminRegistrationScreen)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.new_user_registration),
                        contentDescription = stringResource(id = R.string.new_user_registration_img)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(
                        text = stringResource(id = R.string.New_or_Edit_User_Registration),
                        color = OnTimeColors.White
                    )
                    Text(text = stringResource(id = R.string.Registration), color = OnTimeColors.White)
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        superAdminScreenRoot(SuperAdminScreenRoot.FobRegisterScreen)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.nfc_register),
                        contentDescription = stringResource(id = R.string.fob_register_img)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(
                        text = stringResource(id = R.string.FOB_Register),
                        color = OnTimeColors.White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        superAdminScreenRoot(SuperAdminScreenRoot.SuperAdminSettingScreen)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.device_setting),
                        contentDescription = stringResource(id = R.string.device_setting_img)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(
                        text = stringResource(id = R.string.Device_Setting),
                        color = OnTimeColors.White
                    )

                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.deregister_admin),
                        contentDescription = stringResource(id = R.string.deregister_admin_img)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "Deregister Admins", color = OnTimeColors.White)
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.choose_logo),
                        contentDescription = stringResource(id = R.string.choose_logo_img)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(
                        text = stringResource(id = R.string.Choose_Logo),
                        color = OnTimeColors.White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.export_db),
                        contentDescription = stringResource(id = R.string.export_db_img)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = stringResource(id = R.string.Export_DB), color = OnTimeColors.White)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.Fingerprint_Match_Rate),
                    color = OnTimeColors.White
                )
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.fingerprintMatchRateButtonWidth,
                        height = MaterialTheme.dimens.fingerprintMatchRateButtonHeight
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = OnTimeColors.LightPink),
                    border = BorderStroke(
                        width = MaterialTheme.dimens.superAdminScreenButtonsBorderWidth,
                        color = OnTimeColors.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.Fingerprint_Match_Rate),
                        color = OnTimeColors.DarkGray
                    )
                }
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(containerColor = OnTimeColors.GREEN_HAZE),
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminScreenSubmitButtonWidth,
                        height = MaterialTheme.dimens.superAdminScreenSubmitButtonHeight
                    )
                ) {
                    Text(text = stringResource(id = R.string.SUBMIT))
                }
            }
            Spacer(modifier = Modifier.weight(4f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.dimens.superAdminScreenBottomRowBottomPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        superAdminScreenRoot(SuperAdminScreenRoot.HomeScreen)
                    },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
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
                        Text(
                            text = stringResource(id = R.string.Log_out_and_return_to_home_page),
                        )
                    }
                }
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
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
                            colorFilter = ColorFilter.tint(color = OnTimeColors.MANTIS),
                            modifier = Modifier.size(MaterialTheme.dimens.circleGreenImgSize)
                        )
                        Text(
                            text = stringResource(id = R.string.View_Employee_Online),
                        )
                    }
                }
            }
        }
    }
}