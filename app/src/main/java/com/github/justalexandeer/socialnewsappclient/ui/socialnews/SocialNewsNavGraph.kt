package com.github.justalexandeer.socialnewsappclient.ui.socialnews

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.justalexandeer.socialnewsappclient.ui.SocialNewsAppClientDestinations
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.createnews.CreateNews
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.newsline.NewsLine
import com.github.justalexandeer.socialnewsappclient.ui.socialnews.user.User

fun NavGraphBuilder.socialNewsGraph(
    navController: NavHostController,
    navigation: SocialNewsNavigation,
    setBottomNavigationVisibility: (Boolean) -> Unit
) {
    navigation(
        startDestination = SocialNewsDestinations.NEWS_LINE_ROUTE,
        route = SocialNewsAppClientDestinations.SOCIAL_NEWS_ROUTE
    ) {
        composable(SocialNewsDestinations.NEWS_LINE_ROUTE) {
            setBottomNavigationVisibility(true)
            NewsLine(navController = navController)
        }
        composable(SocialNewsDestinations.CREATE_NEWS_ROUTE) {
            setBottomNavigationVisibility(true)
            CreateNews()
        }
        composable(SocialNewsDestinations.USER_INFO_ROUTE) {
            setBottomNavigationVisibility(true)
            User()
        }
    }
}