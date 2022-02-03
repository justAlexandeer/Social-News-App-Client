package com.github.justalexandeer.socialnewsappclient.ui.authorization

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.justalexandeer.socialnewsappclient.ui.SocialNewsAppClientDestinations
import com.github.justalexandeer.socialnewsappclient.ui.authorization.login.LoginScreen
import com.github.justalexandeer.socialnewsappclient.ui.authorization.onboarding.OnboardingScreen
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.RegistrationScreen
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.SocialNewsNavigation
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalPagerApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalAnimationGraphicsApi
fun NavGraphBuilder.authorizationGraph(
    navController: NavHostController,
    navigation: AuthorizationNavigation,
    setBottomNavigationVisibility: (Boolean) -> Unit
) {
    navigation(
        startDestination = AuthorizationDestinations.ONBOARDING,
        route = SocialNewsAppClientDestinations.AUTHORIZATION_ROUTE
    ) {
        composable(AuthorizationDestinations.ONBOARDING) {
            setBottomNavigationVisibility(false)
            OnboardingScreen(navigation = navigation)
        }
        composable(AuthorizationDestinations.LOGIN) {
            setBottomNavigationVisibility(false)
            LoginScreen(navigation = navigation)
        }
        composable(AuthorizationDestinations.REGISTRATION) {
            setBottomNavigationVisibility(false)
            RegistrationScreen(navigation = navigation)
        }
    }
}