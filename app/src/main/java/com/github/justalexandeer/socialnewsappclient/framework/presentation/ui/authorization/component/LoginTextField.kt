package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun LoginTextField(
    loginTextFieldState: LoginTextFieldState,
    userNameTextFieldChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = loginTextFieldState.text,
        label = { Text(text = stringResource(R.string.username)) },
        singleLine = true,
        onValueChange = {
            userNameTextFieldChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
    )
}

data class LoginTextFieldState(
    val text: String
)