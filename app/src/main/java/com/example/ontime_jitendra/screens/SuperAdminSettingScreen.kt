package com.example.ontime_jitendra.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ontime_jitendra.R
import com.example.ontime_jitendra.ui.theme.dimens
import com.example.ontime_jitendra.widgets.InputField

@Composable
fun SuperAdminSettingScreen(navController: NavController) {
    val checkBoxState = remember {
        mutableStateOf(false)
    }
    val searchState = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = Color.DarkGray.copy(alpha = 0.8f)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Super Admin Setting",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF85D32C),
                fontWeight = FontWeight.Bold
            )
            Surface(
                modifier = Modifier
                    .size(
                        width = 900.dp,
                        height = 400.dp
                    )
                    .padding(top = 10.dp),
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
                            text = "Trust/Organization",
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        InputField(
                            valueState = searchState,
                            labelId = "",
                            enabled = true,
                            isSingleLine = true,
                            modifier = Modifier
                                .size(
                                    width = 400.dp,
                                    height = 70.dp
                                ),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Location(Do not add semi colon)",
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        InputField(
                            valueState = searchState,
                            labelId = "",
                            enabled = true,
                            isSingleLine = true,
                            modifier = Modifier
                                .size(
                                    width = 400.dp,
                                    height = 70.dp
                                ),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Postcode",
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        InputField(
                            valueState = searchState,
                            labelId = "",
                            enabled = true,
                            isSingleLine = true,
                            modifier = Modifier
                                .size(
                                    width = 400.dp,
                                    height = 70.dp
                                ),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = "Unique Identifier",
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        InputField(
                            valueState = searchState,
                            labelId = "",
                            enabled = true,
                            isSingleLine = true,
                            modifier = Modifier
                                .size(
                                    width = 400.dp,
                                    height = 70.dp
                                ),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "LatLng",
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        InputField(
                            valueState = searchState,
                            labelId = "",
                            enabled = true,
                            isSingleLine = true,
                            modifier = Modifier
                                .size(
                                    width = 400.dp,
                                    height = 70.dp
                                ),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(3.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),
                          modifier = Modifier.size(width = 400.dp, height = 70.dp)
                              .padding(10.dp)

                            ) {
                            Text(text = "REGISTER")

                        }
                    }

                }


            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Clock In/Out",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Switch(
                    checked = true,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray,
                        checkedThumbColor = Color.Cyan,
                        checkedTrackColor = Color.White
                    )
                )
                Text(
                    text = "Swipe And Go",
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
                    text = "Enable two way Authentication",
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Enable Pin",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Switch(
                    checked = true,
                    onCheckedChange = {},
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.LightGray,
                        checkedThumbColor = Color.Cyan,
                        checkedTrackColor = Color.White
                    )
                )
                Text(
                    text = "Disable Pin",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )

            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),

                    ) {
                    Text(text = "CHECK FOR UPDATE")

                }
                Spacer(modifier = Modifier.width(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(3.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF85D32C)),

                    ) {
                    Text(text = "SET TEST EMPLOYEE")

                }
            }
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