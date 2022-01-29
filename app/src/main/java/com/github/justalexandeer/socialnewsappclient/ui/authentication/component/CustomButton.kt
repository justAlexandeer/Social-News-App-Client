package com.github.justalexandeer.socialnewsappclient.ui.authentication.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun CustomButton(
    enabled: Boolean,
    sendEvent: () -> Unit
) {
    Button(
        onClick = {
            sendEvent()
        },
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(0.5.dp, Color.Gray),
        colors = ButtonDefaults.buttonColors(
            if (isSystemInDarkTheme()) {
                colorResource(R.color.button_login_register_background_dark_color)
            } else {
                Color.White
            }
        ),
        modifier = Modifier
            .requiredHeight(54.dp)
            .padding(
                horizontal = dimensionResource(R.dimen.grid_6),
            )
            .fillMaxWidth()

    ) {
        Text(
            text = stringResource(R.string.button_sign_in)
        )
    }
}