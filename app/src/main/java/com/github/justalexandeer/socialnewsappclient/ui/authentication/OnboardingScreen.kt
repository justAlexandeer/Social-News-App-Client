package com.github.justalexandeer.socialnewsappclient.ui.authentication

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnboardingScreen(listOfOnboardingPage: List<OnboardingPage>) {

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
            .padding(top = 16.dp)
    ) {
        Column(Modifier.weight(5F)) {
            ViewPager(
                pagerState,
                listOfOnboardingPage
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(2F)
                .fillMaxSize()
        ) {
            PagerIndicator(
                pagerState = pagerState,
                activeColor = MaterialTheme.colors.primary,
            )
            LoginRegistrationButton()
        }
    }
}