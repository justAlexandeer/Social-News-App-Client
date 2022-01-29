package com.github.justalexandeer.socialnewsappclient.ui.authentication

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPage
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.LoginRegistrationButton
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.PagerIndicator
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.math.MathUtils
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import okhttp3.internal.wait

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    listOfOnboardingPage: List<OnboardingPage>
) {

    val pagerState = rememberPagerState()

    OnboardingContent(
        pagerState,
        listOfOnboardingPage
    )

}

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnboardingContent(
    pagerState: PagerState,
    listOfOnboardingPage: List<OnboardingPage>
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(R.dimen.grid_2))
    ) {
        Column(Modifier.weight(1f)) {
            ViewPager(
                pagerState,
                listOfOnboardingPage
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.primary,
            )
            LoginRegistrationButton()
        }
    }

}