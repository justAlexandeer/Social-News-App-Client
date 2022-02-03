package com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun NameTextField(
    nameTextFieldState: NameTextFieldState,
    nameTextFieldChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = nameTextFieldState.text,
        label = { Text(text = stringResource(R.string.name)) },
        singleLine = true,
        onValueChange = {
            nameTextFieldChange(it)
        },
        modifier = modifier
            .fillMaxWidth(),
    )
}

data class NameTextFieldState(
    val text: String
)