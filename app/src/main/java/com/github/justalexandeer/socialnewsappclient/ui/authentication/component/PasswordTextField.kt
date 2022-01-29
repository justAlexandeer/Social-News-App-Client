package com.github.justalexandeer.socialnewsappclient.ui.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun PasswordTextField(
    textPassword: String,
    setTextPassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showPassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = textPassword,
        label = { Text(text = stringResource(R.string.password)) },
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        imageVector = Icons.Filled.Visibility,
                        contentDescription = null
                    )
                }
            } else {
                IconButton(onClick = { showPassword = true }) {
                    Icon(
                        imageVector = Icons.Filled.VisibilityOff,
                        contentDescription = null
                    )
                }
            }
        },
        singleLine = true,
        onValueChange = {
            setTextPassword(it)
        },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Password
        ),
        modifier = modifier
            .fillMaxWidth(),
    )
}