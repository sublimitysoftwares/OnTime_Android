package com.allocate.ontime.presentation_logic.screens.home

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.allocate.ontime.R
import com.allocate.ontime.presentation_logic.navigation.HomeScreenRoot
import com.allocate.ontime.presentation_logic.theme.dimens
import com.allocate.ontime.presentation_logic.screens.login.PinEntryDialog


@Composable
fun HomeScreen(homeScreenRoot: (HomeScreenRoot) -> Unit) {
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
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.small1),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.dimens.small3, top = MaterialTheme.dimens.medium1),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(selected = true, onClick = { /*TODO*/ })

                    Text(
                        text = "2:23 PM",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.rld_logo),
                    contentDescription =
                    "rld_logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth(MaterialTheme.dimens.homeScreenRldLogoMaxWidthFraction)
                        .fillMaxHeight(MaterialTheme.dimens.homeScreenRldLogoMaxHeightFraction)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fingerprint_rld),
                        contentDescription =
                        "place_finger_logo",
                        modifier = Modifier.size(MaterialTheme.dimens.medium3)
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                    Text(
                        text = "Place Finger",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Red
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ontime_logo),
                contentDescription = "onTime_logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth(MaterialTheme.dimens.homeScreenOnTimeLogoMaxWidthFraction)
                    .fillMaxHeight(MaterialTheme.dimens.homeScreenOnTimeLogoMaxHeightFraction)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.dimens.medium1),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rld_img_logo),
                    contentDescription = "rld_img_logo",
                    modifier = Modifier
                        .size(MaterialTheme.dimens.large3)
                        .aspectRatio(1f),

                    )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = MaterialTheme.dimens.small3)
                ) {
                    Button(
                        onClick = {
                            isDialogVisible = true
                        },
                        shape = RoundedCornerShape(MaterialTheme.dimens.homeScreenButtonsCornerShapeSize),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F3341)),
                    ) {
                        Text(text = "ENTER PIN", style = MaterialTheme.typography.titleLarge)

                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.small3))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                homeScreenRoot(HomeScreenRoot.AdminScreen)
                            },
                            shape = RoundedCornerShape(MaterialTheme.dimens.homeScreenButtonsCornerShapeSize),
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
                        Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacerWidth15))
                        Button(
                            onClick = {
                                homeScreenRoot(HomeScreenRoot.SuperAdminScreen)
                            },
                            shape = RoundedCornerShape(MaterialTheme.dimens.homeScreenButtonsCornerShapeSize),
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
                            modifier = Modifier.size(MaterialTheme.dimens.medium3)
                        )
                        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight2))
                        Text(
                            text = "FOB",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Red,
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(bottom = MaterialTheme.dimens.homeScreenBottomRowBottomPadding)
                    ) {
                        Text(text = "6.5d : 6.5", style = MaterialTheme.typography.labelSmall)
                        Text(
                            text = "Unique Identifier : ci delhi",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}


