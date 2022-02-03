package com.github.justalexandeer.socialnewsappclient.ui.authorization

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.github.justalexandeer.socialnewsappclient.ui.SocialNewsAppClientDestinations

class AuthorizationNavigation(navController: NavHostController) {
    val navigateToLoginScreen: () -> Unit = {
        navController.navigate(AuthorizationDestinations.LOGIN) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToRegistrationScreen: () -> Unit = {
        navController.navigate(AuthorizationDestinations.REGISTRATION) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSocialMediaGraph: () -> Unit = {
        navController.navigate(SocialNewsAppClientDestinations.SOCIAL_NEWS_ROUTE) {
            popUpTo(AuthorizationDestinations.ONBOARDING) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}