package com.allocate.ontime.presentation_logic.screens.super_admin


import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.allocate.ontime.R
import com.allocate.ontime.presentation_logic.theme.dimens
import com.allocate.ontime.presentation_logic.widgets.InputField
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AdminRegistrationScreen(navController: NavController) {

    val searchState = remember {
        mutableStateOf("")
    }

    val checkBoxState = remember {
        mutableStateOf(false)
    }

//    var response by remember { mutableStateOf<DeviceInfo?>(null) }
//    val api = Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(OnTimeApi::class.java)
//    GlobalScope.launch(Dispatchers.IO) {
//        val result = api.getDeviceInfo()
//        response = result
//
//        Log.d("abc", "onCreate: ${response.toString()}")
//    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.DarkGray.copy(alpha = 0.8f)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "ADMIN PAGE",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF85D32C),
                fontWeight = FontWeight.Bold
            )
            InputField(
                valueState = searchState,
                labelId = "Search Employee",
                enabled = true,
                isSingleLine = true,
                modifier = Modifier
                    .size(width = 300.dp, height = 70.dp)
                    .align(alignment = Alignment.End)
                    .padding(end = 10.dp),
                textStyle = MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .weight(1f)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(text = "New/Edit User Registration", color = Color.White)
                    Surface(
                        modifier = Modifier
                            .size(
                                width = MaterialTheme.dimens.surfaceWidth,
                                height = MaterialTheme.dimens.surfaceHeight
                            )
//                            .fillMaxWidth(0.4f)
//                            .fillMaxHeight(0.5f)
                            .padding(top = 5.dp),
                        color = Color(0xFF5A5656)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            InputField(
                                valueState = searchState,
                                labelId = "First Name",
                                enabled = true,
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
                                labelId = "Last Name",
                                enabled = true,
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
                                labelId = "Employee Number",
                                enabled = true,
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
                    Text(text = "Fingerprint Registration", color = Color.White)
                    Surface(
                        modifier = Modifier
                            .size(
                                width = MaterialTheme.dimens.surfaceWidth,
                                height = MaterialTheme.dimens.surfaceHeight
                            )
//                            .fillMaxWidth(0.6f)
//                            .fillMaxHeight(0.4f)
                            .padding(top = 5.dp),
                        color = Color(0xFF5A5656)
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Row(
                                modifier = Modifier.padding(start = 15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                InputField(
                                    valueState = searchState,
                                    labelId = "Fingerprint One(Not Registered)",
                                    enabled = true,
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
                                    contentDescription = "fingerprint_img",
                                    modifier = Modifier.aspectRatio(1.2f),
                                )
                            }
                            Row(
                                modifier = Modifier.padding(start = 15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                InputField(
                                    valueState = searchState,
                                    labelId = "Fingerprint Two(Not Registered)",
                                    enabled = true,
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
                                    contentDescription = "fingerprint_img",
                                    modifier = Modifier.aspectRatio(1.2f),
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(start = 15.dp)
                            ) {

                                Checkbox(
                                    checked = false,
                                    onCheckedChange = { checkBoxState.value },
                                    colors = CheckboxDefaults.colors(uncheckedColor = Color.White)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Skip Fingerprint", color = Color.White)
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
                    contentDescription = "qr_code_img",
                    modifier = Modifier.size(MaterialTheme.dimens.qrCodeLogoSize)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),
                    modifier = Modifier.size(
                        width = MaterialTheme.dimens.nextBtnWidth,
                        height = MaterialTheme.dimens.nextBtnHeight
                    )
                ) {
                    Text(text = "NEXT")

                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Please scan QR Code for instructional video on Finger Scan \nregistrations",
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                style = MaterialTheme.typography.labelSmall
            )
            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color.Black
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_black),
                            contentDescription = "circle_black_img",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(15.dp)
                        )

                        Text(text = "Click here to go back")
                    }

                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.LightGray, contentColor = Color(0xFF5B6F46)
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.White)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_green),
                            contentDescription = "circle_green_img",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(text = "View Employee Online")
                    }

                }
            }

        }

    }

}