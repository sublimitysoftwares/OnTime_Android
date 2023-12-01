package com.example.ontime_jitendra.screens

import WindowInfo
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ontime_jitendra.R
import com.example.ontime_jitendra.navigation.OnTimeScreens
import com.example.ontime_jitendra.widgets.PinEntryDialog

@Composable
fun HomeScreen(navController: NavController, windowInfo: WindowInfo) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    if (isDialogVisible) {
        PinEntryDialog(onDismiss = {
            isDialogVisible = false
        }, onPinEntered = { pin ->
            Toast.makeText(context, "Entered PIN: $pin", Toast.LENGTH_SHORT).show()
        })
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 20.dp, top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(selected = true, onClick = { /*TODO*/ })

                    Text(text = "2:23 PM", style = MaterialTheme.typography.titleLarge)
                }
                Image(
                    painter = painterResource(id = R.drawable.rld_logo),
                    contentDescription =
                    "rld_logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(width = 300.dp, height = 75.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.place_finger_icon),
                        contentDescription =
                        "place_finger_logo"
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Place Finger",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ontime_logo),
                contentDescription = "onTime_logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(width = 300.dp, height = 250.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rld_img_logo),
                    contentDescription = "rld_img_logo",
                    modifier = Modifier
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 20.dp)
                ) {


                    Button(
                        onClick = {
                            isDialogVisible = true
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F3341)),
                    ) {
                        Text(text = "ENTER PIN", style = MaterialTheme.typography.titleLarge)

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(OnTimeScreens.AdminScreen.name)
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    0xFF202424
                                )
                            )
                        ) {
                            Text(
                                text = "Administrator Access",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Button(
                            onClick = {
                                navController.navigate(OnTimeScreens.SuperAdminScreen.name)
                            }, shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(
                                    0xFF202424
                                )
                            )
                        ) {
                            Text(
                                text = "View Employee Online",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fob_icon),
                        contentDescription = "fob_icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.align(alignment = Alignment.End)

                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "FOB",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Red,
                        modifier = Modifier
                            .align(alignment = Alignment.End)
                            .padding(end = 6.dp)
                    )
                    Spacer(modifier = Modifier.height(60.dp))
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(text = "6.5d : 6.5", style = MaterialTheme.typography.labelSmall)
                        Text(text = "Unique Identifier : ci delhi", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }



        }
    }


