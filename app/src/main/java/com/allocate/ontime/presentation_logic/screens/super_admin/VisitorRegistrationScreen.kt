package com.allocate.ontime.presentation_logic.screens.super_admin


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.utils.OnTimeColors
import com.allocate.ontime.presentation_logic.navigation.SuperAdminScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens
import com.allocate.ontime.presentation_logic.widgets.InputField


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun VisitorRegistrationScreen(backToSuperAdminScreen: (SuperAdminScreenRoot) -> Unit) {

    val searchVisitor = remember {
        mutableStateOf("")
    }
    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    val companyName = remember {
        mutableStateOf("")
    }
    val visitingPerson = remember {
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val vehicleRegistration = remember {
        mutableStateOf("")
    }
    val reason = remember {
        mutableStateOf("")
    }
    val searchState = remember {
        mutableStateOf("")
    }

    val checkBoxState = remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = OnTimeColors.TORY_BLUE
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
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
                                height = MaterialTheme.dimens.visitorRegistrationScreenUserRegistrationSurfaceHeight
                            )
                            .padding(top = MaterialTheme.dimens.adminRegistrationScreenSurfaceTopPadding),
                        color = OnTimeColors.PORT_GORE
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            InputField(
                                valueState = searchVisitor,
                                labelId = stringResource(id = R.string.Search_Visitor),
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.userInputTextFieldHeight
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )
                            Row {
                                InputField(
                                    valueState = firstName,
                                    labelId = stringResource(id = R.string.First_Name),
                                    isSingleLine = true,
                                    modifier = Modifier
                                        .size(
                                            width = MaterialTheme.dimens.visitorRegistrationScreenInputFieldWidth,
                                            height = MaterialTheme.dimens.userInputTextFieldHeight
                                        ),
                                    textStyle = MaterialTheme.typography.titleMedium
                                )
                                InputField(
                                    valueState = lastName,
                                    labelId = stringResource(id = R.string.Last_Name),
                                    isSingleLine = true,
                                    modifier = Modifier
                                        .size(
                                            width = MaterialTheme.dimens.visitorRegistrationScreenInputFieldWidth,
                                            height = MaterialTheme.dimens.userInputTextFieldHeight
                                        ),
                                    textStyle = MaterialTheme.typography.titleMedium
                                )
                            }
                            Row {
                                InputField(
                                    valueState = companyName,
                                    labelId = stringResource(id = R.string.Company_name),
                                    isSingleLine = true,
                                    modifier = Modifier
                                        .size(
                                            width = MaterialTheme.dimens.visitorRegistrationScreenInputFieldWidth,
                                            height = MaterialTheme.dimens.userInputTextFieldHeight
                                        ),
                                    textStyle = MaterialTheme.typography.titleMedium
                                )
                                InputField(
                                    valueState = visitingPerson,
                                    labelId = stringResource(id = R.string.Visiting_person),
                                    isSingleLine = true,
                                    modifier = Modifier
                                        .size(
                                            width = MaterialTheme.dimens.visitorRegistrationScreenInputFieldWidth,
                                            height = MaterialTheme.dimens.userInputTextFieldHeight
                                        ),
                                    textStyle = MaterialTheme.typography.titleMedium
                                )
                            }
                            InputField(
                                valueState = phoneNumber,
                                labelId = stringResource(id = R.string.Phone_number),
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.userInputTextFieldHeight
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )
                            InputField(
                                valueState = vehicleRegistration,
                                labelId = stringResource(id = R.string.Vehicle_Registration),
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.userInputTextFieldHeight
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )
                            InputField(
                                valueState = reason,
                                labelId = stringResource(id = R.string.Reason),
                                isSingleLine = false,
                                modifier = Modifier
                                    .size(
                                        width = MaterialTheme.dimens.userInputTextFieldWidth,
                                        height = MaterialTheme.dimens.visitorRegistrationScreenReasonInputFieldWidth
                                    ),
                                textStyle = MaterialTheme.typography.titleMedium
                            )

                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .size(
                            width = MaterialTheme.dimens.surfaceWidth,
                            height = MaterialTheme.dimens.visitorRegistrationScreenRightSurfaceHeight
                        )
                        .padding(top = MaterialTheme.dimens.visitorRegistrationScreenRightSurfaceTopPadding),
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
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight10))
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.login),
                                contentDescription = stringResource(id = R.string.fingerprint_img),
                                modifier = Modifier.size(MaterialTheme.dimens.visitorRegistrationScreenImgSize),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.logout),
                                contentDescription = stringResource(id = R.string.fingerprint_img),
                                modifier = Modifier.size(MaterialTheme.dimens.visitorRegistrationScreenImgSize),
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.Clock_in),
                                style = MaterialTheme.typography.headlineSmall,
                                color = OnTimeColors.GREEN_HAZE
                            )
                            Text(
                                text = stringResource(id = R.string.Clock_out),
                                style = MaterialTheme.typography.headlineSmall,
                                color = OnTimeColors.ALIZARIN_CRIMSON
                            )

                        }
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerWidth20))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(MaterialTheme.dimens.adminRegistrationScreenButtonsCornerShapeSize),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OnTimeColors.GREEN_HAZE,
                                contentColor = OnTimeColors.White
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            contentPadding = PaddingValues(MaterialTheme.dimens.visitorRegistrationScreenRegisterButtonContentPadding)
                        ) {
                            Text(text = stringResource(id = R.string.Register_and_make_event))
                        }


                    }
                }
            }
            Spacer(
                modifier = Modifier
                    .weight(MaterialTheme.dimens.visitorRegistrationScreenSpacerWeight)
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
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.circleImgAndTextSpace),
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
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.circleImgAndTextSpace),
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