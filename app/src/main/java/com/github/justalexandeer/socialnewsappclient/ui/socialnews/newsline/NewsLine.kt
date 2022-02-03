package com.github.justalexandeer.socialnewsappclient.ui.socialnews.newsline;

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun NewsLine(
    viewModel: NewsLineViewModel = hiltViewModel(),
    navController: NavController
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "News Line")
        Button(onClick = {
            Log.i("TAG", "NewsLine: ${navController.graph.nodes}")
            navController.navigate("asdfsadf")
        //navController.navigate()
        }) {
            Text(text = "в вложенную навигацию")
        }
    }
}