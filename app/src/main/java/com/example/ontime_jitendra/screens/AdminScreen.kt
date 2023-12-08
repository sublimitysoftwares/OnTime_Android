package com.example.ontime_jitendra.screens

import WindowInfo
import android.util.Log
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
import androidx.compose.material3.RadioButtonDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ontime_jitendra.R
import com.example.ontime_jitendra.navigation.OnTimeScreens
import com.example.ontime_jitendra.ui.theme.dimens
import com.example.ontime_jitendra.widgets.PinEntryDialog

@Composable
fun AdminScreen(navController: NavController, windowInfo: WindowInfo) {

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
        modifier = Modifier.fillMaxSize(), color = Color.DarkGray.copy(alpha = 0.8f)
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
                    RadioButton(
                        selected = true,
                        onClick = { /*TODO*/ },
                        colors = RadioButtonDefaults.colors(selectedColor = Color.White)
                    )

                    Text(
                        text = "2:23 PM",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column {
                    Text(
                        text = "WELCOME TO ADMIN PAGE",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFF008B8B),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Administrator to Log In",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    Text(
                        text = "via the Fingerprint Reader",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White,
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fingerprint_rld),
                        contentDescription = "place_finger_logo",
                        colorFilter = ColorFilter.tint(color = Color.White),
                        modifier = Modifier.size(MaterialTheme.dimens.medium3)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Place Finger",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.45f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {
                Image(
                    painter = painterResource(id = R.drawable.rld_img_logo),
                    contentDescription = "rld_img_logo",
                    modifier = Modifier.size(MaterialTheme.dimens.large3)
                        .aspectRatio(1f),
                    colorFilter = ColorFilter.tint(color = Color.White)
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
                        Text(
                            text = "ENTER PIN",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(OnTimeScreens.HomeScreen.name)
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF008B8B)
                            )
                        ) {
                            Text(
                                text = "Click here to home page",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF008B8B)
                            )
                        ) {
                            Text(
                                text = "View Employee Online",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_nfc),
                            contentDescription = "fob_icon",
                            contentScale = ContentScale.Fit,
                            colorFilter = ColorFilter.tint(color = Color.White),
                            modifier = Modifier.size(MaterialTheme.dimens.medium3)

                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "FOB",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                        )

                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(bottom = 20.dp)
                    ) {
                        Text(text = "6.5d : 6.5", color = Color.White,style = MaterialTheme.typography.labelSmall)
                        Text(text = "Unique Identifier : ci delhi", color = Color.White,style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}





