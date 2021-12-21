package com.github.justalexandeer.socialnewsappclient.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class SocialNewsNavigation(navController: NavHostController) {
    val navigateToNewsLine: () -> Unit = {
        navController.navigate(SocialNewsDestinations.NEWS_LINE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToCreateNews: () -> Unit = {
        navController.navigate(SocialNewsDestinations.CREATE_NEWS_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToUserInfo: () -> Unit = {
        navController.navigate(SocialNewsDestinations.USER_INFO_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToAuthentication: () -> Unit = {
        navController.navigate(SocialNewsDestinations.AUTHENTICATION_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}