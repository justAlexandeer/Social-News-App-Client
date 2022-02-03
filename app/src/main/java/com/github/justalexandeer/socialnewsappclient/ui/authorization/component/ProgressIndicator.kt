package com.github.justalexandeer.socialnewsappclient.ui.authorization.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun ProgressIndicator(
    circularProgressIndicatorState: CircularProgressIndicatorState,
    modifier: Modifier = Modifier
) {
    if (circularProgressIndicatorState.isVisible) {
        Column(
            modifier
        ) {
            CircularProgressIndicator(
                Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

data class CircularProgressIndicatorState(
    val isVisible: Boolean
)