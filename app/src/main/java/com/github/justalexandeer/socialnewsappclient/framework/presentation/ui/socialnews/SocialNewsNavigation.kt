package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.socialnews

import androidx.navigation.NavHostController
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.SocialNewsAppClientDestinations

class SocialNewsNavigation(navController: NavHostController) {
    val navigateToNewsLine: () -> Unit = {
        navController.navigate(SocialNewsDestinations.NEWS_LINE_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToCreateNews: () -> Unit = {
        navController.navigate(SocialNewsDestinations.CREATE_NEWS_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToUserInfo: () -> Unit = {
        navController.navigate(SocialNewsDestinations.USER_INFO_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToAuthorizationGraph: () -> Unit = {
        navController.navigate(SocialNewsAppClientDestinations.AUTHORIZATION_ROUTE) {
            launchSingleTop = true
            restoreState = true
        }
    }
}