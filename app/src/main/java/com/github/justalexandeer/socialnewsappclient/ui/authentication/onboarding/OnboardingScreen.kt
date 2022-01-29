package com.github.justalexandeer.socialnewsappclient.ui.authentication.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPage
import com.github.justalexandeer.socialnewsappclient.ui.authentication.onboarding.component.LoginRegistrationButton
import com.github.justalexandeer.socialnewsappclient.ui.authentication.onboarding.component.PagerIndicator
import com.github.justalexandeer.socialnewsappclient.ui.authentication.onboarding.component.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    //onRegisterButtonClick: () -> Unit,
    //onLoginButtonClick: () -> Unit,
) {

    val onboardingViewModel: OnboardingViewModel = viewModel()
    val pagerState = rememberPagerState()

    OnboardingContent(
        pagerState,
        onboardingViewModel.listOfOnboardingPages
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