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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.allocate.ontime.R
import com.allocate.ontime.presentation_logic.navigation.SuperAdminScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens

@Composable
fun SuperAdminScreen(superAdminScreenRoot: (SuperAdminScreenRoot) -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray.copy(alpha = MaterialTheme.dimens.surfaceColorAlphaValue)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = MaterialTheme.dimens.superAdminScreenColumnTopPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "SUPER ADMIN PAGE",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF85D32C),
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .size(
                        width = MaterialTheme.dimens.switchToMobileModeBtnWidth,
                        height = MaterialTheme.dimens.switchToMobileModeBtnHeight
                    )
                    .padding(end = MaterialTheme.dimens.switchToMobileModeBtnEndPadding),
                shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize)
            ) {
                Text(text = "    switch to \nmobile mode")
            }
            Spacer(modifier = Modifier.weight(3f))
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
                        contentDescription = "new_user_registration_img"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "New/Edit Admin", color = Color.White)
                    Text(text = "Registration", color = Color.White)
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
                        contentDescription = "fob_register_img"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "FOB Register", color = Color.White)
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
                        contentDescription = "device_setting_img"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "Device Setting", color = Color.White)

                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.deregister_admin),
                        contentDescription = "deregister_admin_img"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "Deregister Admins", color = Color.White)
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.choose_logo),
                        contentDescription = "choose_logo_img"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "Choose Logo", color = Color.White)
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.export_db),
                        contentDescription = "export_db_img"
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(text = "Export DB", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Fingerprint Match Rate", color = Color.White)
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.fingerprintMatchRateButtonWidth,
                        height = MaterialTheme.dimens.fingerprintMatchRateButtonHeight
                    ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD6B6C1)),
                    border = BorderStroke(
                        width = MaterialTheme.dimens.superAdminScreenButtonsBorderWidth,
                        color = Color.White
                    )
                ) {
                    Text(text = "Fingerprint Match Rate", color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.superAdminScreenSubmitButtonWidth,
                        height = MaterialTheme.dimens.superAdminScreenSubmitButtonHeight
                    )
                ) {
                    Text(text = "SUBMIT")
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
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    border = BorderStroke(
                        width = MaterialTheme.dimens.superAdminScreenButtonsBorderWidth,
                        color = Color.White
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_black),
                            contentDescription = "circle_black_img",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(MaterialTheme.dimens.circleBlackImgSize)
                        )
                        Text(text = "Log out and return to home page")
                    }
                }
                Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth20))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(MaterialTheme.dimens.superAdminScreenButtonsCornerShapeSize),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color(0xFF5B6F46)
                    ),
                    border = BorderStroke(
                        width = MaterialTheme.dimens.superAdminScreenButtonsBorderWidth,
                        color = Color.White
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_green),
                            contentDescription = "circle_green_img",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(MaterialTheme.dimens.circleGreenImgSize)
                        )
                        Text(text = "View Employee Online")
                    }
                }
            }
        }
    }
}