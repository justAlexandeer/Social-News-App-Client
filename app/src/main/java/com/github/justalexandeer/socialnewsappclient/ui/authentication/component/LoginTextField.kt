package com.github.justalexandeer.socialnewsappclient.ui.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun LoginTextField(
    textUserName: String,
    setTextUserName: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = textUserName,
        label = { Text(text = stringResource(R.string.username)) },
        singleLine = true,
        onValueChange = {
            setTextUserName(it)
        },
        modifier = modifier
            .fillMaxWidth(),
    )
}