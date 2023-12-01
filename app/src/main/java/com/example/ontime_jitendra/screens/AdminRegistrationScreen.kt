package com.example.ontime_jitendra.screens

import WindowInfo
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import com.example.ontime_jitendra.R
import com.example.ontime_jitendra.widgets.InputField

@Composable
fun AdminRegistrationScreen(navController: NavController, windowInfo: WindowInfo) {
    val searchState = remember {
        mutableStateOf("")
    }

    val checkBoxState = remember {
        mutableStateOf(false)
    }

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
                    .padding(end = 10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))


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
                            .size(width = 465.dp, height = 220.dp)
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
                                    .size(width = 435.dp, height = 70.dp)
                            )
                            InputField(
                                valueState = searchState,
                                labelId = "Last Name",
                                enabled = true,
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(width = 435.dp, height = 70.dp)
                            )
                            InputField(
                                valueState = searchState,
                                labelId = "Employee Number",
                                enabled = true,
                                isSingleLine = true,
                                modifier = Modifier
                                    .size(width = 435.dp, height = 70.dp)
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
                            .size(width = 465.dp, height = 220.dp)
                            .padding(top = 5.dp),
                        color =  Color(0xFF5A5656)
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
                                        .size(width = 370.dp, height = 70.dp),
                                    isTrailingIcon = true
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.fingerprint),
                                    contentDescription = "fingerprint_img"
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
                                        .size(width = 370.dp, height = 70.dp),
                                    isTrailingIcon = true
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.fingerprint),
                                    contentDescription = "fingerprint_img"
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
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ontime_qr_code),
                    contentDescription = "qr_code_img",
                    modifier = Modifier.size(width = 50.dp, height = 50.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),
                    modifier = Modifier.size(width = 200.dp, height = 40.dp)
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
            Spacer(modifier = Modifier.height(10.dp))
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
                            contentDescription = "circle_black_img", contentScale = ContentScale.Fit,
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
                            contentDescription = "circle_green_img", contentScale = ContentScale.Fit,
                            modifier = Modifier.size(15.dp)
                        )
                        Text(text = "View Employee Online")
                    }

                }
            }

        }

    }

}