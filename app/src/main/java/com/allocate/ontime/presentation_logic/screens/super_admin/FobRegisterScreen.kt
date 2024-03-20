package com.allocate.ontime.presentation_logic.screens.super_admin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.utils.OnTimeColors
import com.allocate.ontime.presentation_logic.navigation.SuperAdminScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens
import com.allocate.ontime.presentation_logic.widgets.InputField

@Composable
fun FobRegisterScreen(backToSuperAdminScreen: (SuperAdminScreenRoot) -> Unit) {

    val searchEmployeeState = remember {
        mutableStateOf("")
    }
    val locationRadiusState = remember {

        mutableStateOf("")
    }

    val isVisible = remember {
        mutableStateOf(true)
    }

    val isSwitchOn = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = OnTimeColors.TORY_BLUE
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                top = MaterialTheme.dimens.s3,
                bottom = MaterialTheme.dimens.s3
            )
        ) {
            Text(
                text = stringResource(id = R.string.FOB_REGISTER),
                style = MaterialTheme.typography.headlineMedium,
                color = OnTimeColors.GREEN_HAZE,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputField(
                    valueState = searchEmployeeState,
                    labelId = stringResource(id = R.string.Search_Employee),
                    isSingleLine = true,
                    modifier = Modifier
                        .size(
                            width = MaterialTheme.dimens.fobRegScrSearchTxtFieldW,
                            height = MaterialTheme.dimens.fobRegScrSearchTxtFieldH
                        )
                        .align(alignment = Alignment.End)
                        .padding(end = MaterialTheme.dimens.fobRegScrSearchTxtFieldEndPad),
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.s1))
                AnimatedVisibility(
                    visible = if (!isSwitchOn.value) {
                        isVisible.value
                    } else {
                        !isVisible.value
                    }, enter = fadeIn(), exit = fadeOut()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.width(MaterialTheme.dimens.fobRegScrSetLocRowW)
                    ) {
                        Text(
                            text = stringResource(id = R.string.Set_location_Radius),
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall
                        )
                        OutlinedTextField(
                            value = locationRadiusState.value,
                            onValueChange = { locationRadiusState.value = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Default
                            ),
                            singleLine = true,
                            modifier = Modifier.size(
                                width = MaterialTheme.dimens.fobRegScrSetLocTxtFieldWidth,
                                height = MaterialTheme.dimens.fobRegScrSetLocTxtFieldH
                            ),
                            textStyle = MaterialTheme.typography.titleSmall,
                            colors = run {
                                val containerColor = OnTimeColors.LightPink
                                TextFieldDefaults.colors(
                                    unfocusedTextColor = OnTimeColors.LightGray,
                                    focusedContainerColor = containerColor,
                                    unfocusedContainerColor = containerColor,
                                    disabledContainerColor = containerColor,
                                    cursorColor = OnTimeColors.Cyan,
                                    focusedIndicatorColor = OnTimeColors.White,
                                    focusedLabelColor = OnTimeColors.White,
                                    focusedSupportingTextColor = OnTimeColors.White,
                                )
                            },
                            placeholder = {
                                Text(
                                    text = stringResource(id = R.string.Miles),
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.s1))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .width(MaterialTheme.dimens.fobRegScrSetLocRowW)
                ) {
                    Text(
                        text = stringResource(id = R.string.All_Locations),
                        color = OnTimeColors.White,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Switch(
                        checked = isSwitchOn.value,
                        onCheckedChange = {
                            isSwitchOn.value = it
                        },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = OnTimeColors.White,
                            uncheckedTrackColor = OnTimeColors.LightGray,
                            checkedThumbColor = OnTimeColors.Cyan,
                            checkedTrackColor = OnTimeColors.White
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(MaterialTheme.dimens.fobRegScrSpacerWeight))
            Button(
                onClick = {
                    backToSuperAdminScreen(SuperAdminScreenRoot.SuperAdminScreen)
                },
                shape = RoundedCornerShape(MaterialTheme.dimens.fobRegScrBtnCornerSz),
                colors = ButtonDefaults.buttonColors(
                    containerColor = OnTimeColors.GREEN_HAZE, contentColor = OnTimeColors.White
                )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.btnRowHArrangementSpacedBy),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.circle_black),
                        contentDescription = stringResource(id = R.string.circle_black_img),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(MaterialTheme.dimens.circleBlkImgSz)
                    )
                    Text(text = stringResource(id = R.string.Click_here_to_go_back))
                }
            }
        }
    }
}