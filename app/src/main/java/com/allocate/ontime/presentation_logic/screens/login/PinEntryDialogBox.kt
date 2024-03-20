package com.allocate.ontime.presentation_logic.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.allocate.ontime.R
import com.allocate.ontime.business_logic.utils.OnTimeColors
import com.allocate.ontime.presentation_logic.theme.dimens

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
                        text = stringResource(id = R.string.Administrator_Access),
                        style = MaterialTheme.typography.headlineSmall,
                        color = OnTimeColors.GREEN_HAZE,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = MaterialTheme.dimens.administrationPadding)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.dimens.adminAndCloseSpacerW))
                    IconButton(
                        onClick = {
                            onDismiss()
                        }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(MaterialTheme.dimens.closeIconSize)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight5))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.pinEntryDialogTextSurfaceHeight),
                    shape = RoundedCornerShape(MaterialTheme.dimens.pinEntryDialogRoundedCornerShapeSize),
                    color = OnTimeColors.LightPink,
                    border = BorderStroke(
                        width = MaterialTheme.dimens.pinEntryDialogTextSurfaceBorderWidth,
                        color = OnTimeColors.ALIZARIN_CRIMSON
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.Please_enter_your_PIN_number_to_log_in),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = MaterialTheme.dimens.enterPinTextPadding)
                    )
                }
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacerHeight5))
                Text(
                    text = stringResource(id = R.string.Administration_Log_In),
                    style = MaterialTheme.typography.headlineSmall,
                    color = OnTimeColors.TORY_BLUE,
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
                colors = ButtonDefaults.buttonColors(containerColor = OnTimeColors.GREEN_HAZE),
                shape = RoundedCornerShape(MaterialTheme.dimens.pinEntryDialogRoundedCornerShapeSize)
            ) {
                Text(text = stringResource(id = R.string.Login))
            }
        },
        dismissButton = {},
        shape = RoundedCornerShape(MaterialTheme.dimens.pinEntryDialogRoundedCornerShapeSize)
    )
}