package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui

import android.util.Log
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.AuthorizationNavigation
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.authorizationGraph
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.socialnews.SocialNewsNavigation
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.socialnews.socialNewsGraph
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationGraphicsApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun SocialNewsAppClientNavGraph(
    needAuthorization: Boolean,
    navController: NavHostController,
    setBottomNavigationVisibility: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = if(needAuthorization) SocialNewsAppClientDestinations.AUTHORIZATION_ROUTE else
            SocialNewsAppClientDestinations.SOCIAL_NEWS_ROUTE
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