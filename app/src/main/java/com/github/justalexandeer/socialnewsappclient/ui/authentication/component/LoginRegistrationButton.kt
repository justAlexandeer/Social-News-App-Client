package com.github.justalexandeer.socialnewsappclient.ui.authentication.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun LoginRegistrationButton(

) {

    Surface(
        shape = MaterialTheme.shapes.large,
        color = if (isSystemInDarkTheme()) {
            colorResource(R.color.button_login_register_background_dark_color)
        } else {
            Color.White
        },
        border = BorderStroke(0.5.dp, Color.Gray),
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(
                start = 50.dp,
                end = 50.dp,
                bottom = dimensionResource(R.dimen.layout_margins)
            )
    ) {
        Row(
            Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(text = stringResource(R.string.button_registration))
            }
            Button(
                onClick = { /*TODO*/ },
                elevation = ButtonDefaults.elevation(0.dp),
                colors = ButtonDefaults.buttonColors(
                    if (isSystemInDarkTheme()) {
                        colorResource(R.color.button_login_register_background_dark_color)
                    } else {
                        Color.White
                    }
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.button_sign_in)
                )
            }
        }

    }

}