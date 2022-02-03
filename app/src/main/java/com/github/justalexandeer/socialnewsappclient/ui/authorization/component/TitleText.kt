package com.github.justalexandeer.socialnewsappclient.ui.authorization.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleText (
    titleText: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = titleText,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}