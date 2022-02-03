package com.github.justalexandeer.socialnewsappclient.ui.authorization.onboarding.component

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
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




