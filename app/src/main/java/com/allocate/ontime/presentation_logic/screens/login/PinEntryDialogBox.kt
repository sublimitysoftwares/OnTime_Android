package com.allocate.ontime.presentation_logic.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.allocate.ontime.presentation_logic.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PinEntryDialog(
    onDismiss: () -> Unit,
    onPinEntered: (String) -> Unit
) {
    var pin by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(
                        text = "ADMINISTRATION ACCESS",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = MaterialTheme.dimens.administrationPadding)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.adminAndCloseSpacerW))

                    IconButton(
                        onClick = {
                            onDismiss()
                        },


                        ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(MaterialTheme.dimens.closeIconSize)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(3.dp),
                    color = Color(0xFFD6B6C1),
                    border = BorderStroke(width = 1.dp, color = Color(0xFFB3798D))
                ) {
                    Text(
                        text = "Please enter your PIN number to log in",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = MaterialTheme.dimens.enterPinTextPadding)
                    )

                }
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Administration Log In",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color(0xFF008B8B),
                    fontWeight = FontWeight.Bold
                )


            }

        },
        text = {
            OutlinedTextField(
                value = pin,
                onValueChange = {
                    pin = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

        },
        confirmButton = {
            Button(
                onClick = {
                    onPinEntered(pin)
                    focusManager.clearFocus()
                    onDismiss()
                },
                modifier = Modifier
                    .padding(end = MaterialTheme.dimens.loginButtonPadding)
                    .size(
                        width = MaterialTheme.dimens.loginButtonWidth,
                        height = MaterialTheme.dimens.loginButtonHeight
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF19701D)),
                shape = RoundedCornerShape(3.dp)
            ) {
                Text(text = "Login")
            }
        },
        dismissButton = {},
        shape = RoundedCornerShape(3.dp)
    )
}