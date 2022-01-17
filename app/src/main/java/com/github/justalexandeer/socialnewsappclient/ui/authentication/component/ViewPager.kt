package com.github.justalexandeer.socialnewsappclient.ui.authentication.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.android.material.animation.AnimationUtils.lerp
import kotlin.math.absoluteValue

@ExperimentalFoundationApi
@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
fun ViewPager(
    pagerState: PagerState,
    listOfOnboardingPage: List<OnboardingPage>
) {
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides null
    ) {

        HorizontalPager(
            count = listOfOnboardingPage.size,
            state = pagerState
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            val currentOnboardingPage = listOfOnboardingPage[page]

            Page(currentOnboardingPage, pageOffset)
        }
    }
}




