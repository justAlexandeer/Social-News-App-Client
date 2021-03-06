package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.onboarding.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.github.justalexandeer.socialnewsappclient.R
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
            .padding(dimensionResource(R.dimen.grid_2))
    )

}