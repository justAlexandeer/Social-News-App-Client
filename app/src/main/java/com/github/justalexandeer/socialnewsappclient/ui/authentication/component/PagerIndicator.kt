package com.github.justalexandeer.socialnewsappclient.ui.authentication.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun PagerIndicator(
    pagerState: PagerState,
    activeColor: Color
) {

    HorizontalPagerIndicator(
        pagerState = pagerState,
        activeColor = activeColor,
        modifier = Modifier
            .padding(16.dp)
    )

}