package com.allocate.ontime.presentation_logic.screens.super_admin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.allocate.ontime.R
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
        color = Color.DarkGray.copy(alpha = MaterialTheme.dimens.surfaceColorAlphaValue)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                top = MaterialTheme.dimens.small3,
                bottom = MaterialTheme.dimens.small3
            )
        ) {
            Text(
                text = "FOB REGISTER",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF85D32C),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputField(
                    valueState = searchEmployeeState,
                    labelId = "Search Employee",
                    enabled = true,
                    isSingleLine = true,
                    modifier = Modifier
                        .size(
                            width = MaterialTheme.dimens.fobRegisterScreenSearchTextFieldWidth,
                            height = MaterialTheme.dimens.fobRegisterScreenSearchTextFieldHeight
                        )
                        .align(alignment = Alignment.End)
                        .padding(end = MaterialTheme.dimens.fobRegisterScreenSearchTextFieldEndPadding),
                    textStyle = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.small1))
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
                        modifier = Modifier.width(MaterialTheme.dimens.fobRegisterScreenSetLocationRowWidth)
                    ) {
                        Text(
                            text = "Set location Radius",
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
                                width = MaterialTheme.dimens.fobRegisterScreenSetLocationTextFieldWidth,
                                height = MaterialTheme.dimens.fobRegisterScreenSetLocationTextFieldHeight
                            ),
                            textStyle = MaterialTheme.typography.titleSmall,
                            colors = run {
                                val containerColor = Color(0xFFD6B6C1)
                                TextFieldDefaults.colors(
                                    unfocusedTextColor = Color.LightGray,
                                    focusedContainerColor = containerColor,
                                    unfocusedContainerColor = containerColor,
                                    disabledContainerColor = containerColor,
                                    cursorColor = Color.Cyan,
                                    focusedIndicatorColor = Color.White,
                                    focusedLabelColor = Color.White,
                                    focusedSupportingTextColor = Color.White,
                                )
                            },
                            placeholder = {
                                Text(
                                    text = "Miles",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.small1))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .width(MaterialTheme.dimens.fobRegisterScreenSetLocationRowWidth)
                ) {
                    Text(
                        text = "All Locations",
                        color = Color.White,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Switch(
                        checked = isSwitchOn.value,
                        onCheckedChange = {
                            isSwitchOn.value = it
                        },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.LightGray,
                            checkedThumbColor = Color.Cyan,
                            checkedTrackColor = Color.White
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    backToSuperAdminScreen(SuperAdminScreenRoot.SuperAdminScreen)
                },
                shape = RoundedCornerShape(MaterialTheme.dimens.fobRegisterScreenButtonsCornerShapeSize),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray, contentColor = Color.Black
                ),
                border = BorderStroke(
                    width = MaterialTheme.dimens.fobRegisterScreenButtonsBorderWidth,
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
                    Text(text = "Click here to go back")
                }
            }
        }
    }
}