package com.github.justalexandeer.socialnewsappclient.ui.newsline;

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment

@Composable
fun NewsLine() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "News Line")
    }
}