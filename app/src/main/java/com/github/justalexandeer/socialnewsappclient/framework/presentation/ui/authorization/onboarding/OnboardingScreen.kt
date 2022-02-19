package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.business.domain.model.OnboardingPage
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.AuthorizationNavigation
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.onboarding.component.LoginRegistrationButton
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.onboarding.component.PagerIndicator
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.onboarding.component.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnboardingScreen(
    onboardingViewModel: OnboardingViewModel = hiltViewModel(),
    navigation: AuthorizationNavigation
) {
    val pagerState = rememberPagerState()

    if (!onboardingViewModel.isFirstStartApp) {
        navigation.navigateToLoginScreenPopUpAll()
    } else {
        OnboardingContent(
            pagerState,
            onboardingViewModel.listOfOnboardingPages,
            navigation,
        )
    }
}

@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun OnboardingContent(
    pagerState: PagerState,
    listOfOnboardingPage: List<OnboardingPage>,
    navigation: AuthorizationNavigation,
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
            LoginRegistrationButton(navigation)
        }
    }

}