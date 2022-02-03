package com.github.justalexandeer.socialnewsappclient.ui

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.justalexandeer.socialnewsappclient.ui.authorization.AuthorizationNavigation
import com.github.justalexandeer.socialnewsappclient.ui.authorization.authorizationGraph
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.RegistrationScreen
import com.github.justalexandeer.socialnewsappclient.ui.authorization.login.LoginScreen
import com.github.justalexandeer.socialnewsappclient.ui.authorization.onboarding.OnboardingScreen
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.SocialNewsNavigation
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.createnews.CreateNews
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.newsline.NewsLine
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.socialNewsGraph
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.user.User
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationGraphicsApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun SocialNewsAppClientNavGraph(
    navController: NavHostController,
    setBottomNavigationVisibility: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = SocialNewsAppClientDestinations.AUTHORIZATION_ROUTE
    ) {
        authorizationGraph(
            navController,
            AuthorizationNavigation(navController),
            setBottomNavigationVisibility
        )
        socialNewsGraph(
            navController,
            SocialNewsNavigation(navController),
            setBottomNavigationVisibility
        )

    }
}