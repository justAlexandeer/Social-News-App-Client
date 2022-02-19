package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SubTitleText(
    subTitleText: String
) {
    Text(
        text = subTitleText,
        style = MaterialTheme.typography.h5,
        maxLines = 2,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}