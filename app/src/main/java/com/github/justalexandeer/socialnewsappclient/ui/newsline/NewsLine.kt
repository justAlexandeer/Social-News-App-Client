package com.github.justalexandeer.socialnewsappclient.ui.newsline;

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NewsLine(
    viewModel: NewsLineViewModel = hiltViewModel<NewsLineViewModel>()
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "News Line")
        Button(onClick = { viewModel.getPost() }) {
            Text(text = "получить пост")
        }
    }
}