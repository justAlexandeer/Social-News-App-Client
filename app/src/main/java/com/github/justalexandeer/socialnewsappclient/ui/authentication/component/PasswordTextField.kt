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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.ui.authentication.login.model.PasswordTextFieldEvent
import com.github.justalexandeer.socialnewsappclient.ui.authentication.login.model.PasswordTextFieldState

@Composable
fun PasswordTextField(
    passwordTextFieldState: PasswordTextFieldState,
    passwordTextFieldChange: (String) -> Unit,
    passwordTextFieldIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = passwordTextFieldState.text,
        label = { Text(text = stringResource(R.string.password)) },
        trailingIcon = {
            IconButton(
                onClick = { passwordTextFieldIconClick() }
            ) {
                Icon(
                    imageVector = if (passwordTextFieldState.passwordVisibility)
                        Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = null
                )
            }

        },
        singleLine = true,
        onValueChange = {
            passwordTextFieldChange(it)
        },
        visualTransformation = if (passwordTextFieldState.passwordVisibility) {
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