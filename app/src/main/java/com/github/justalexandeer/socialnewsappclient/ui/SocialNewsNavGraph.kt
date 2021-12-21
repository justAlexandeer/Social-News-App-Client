package com.github.justalexandeer.socialnewsappclient.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.justalexandeer.socialnewsappclient.ui.authentication.Authentication
import com.github.justalexandeer.socialnewsappclient.ui.createnews.CreateNews
import com.github.justalexandeer.socialnewsappclient.ui.newsline.NewsLine
import com.github.justalexandeer.socialnewsappclient.ui.user.User

@Composable
fun SocialNewsNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SocialNewsDestinations.CREATE_NEWS_ROUTE
    ) {
        composable(SocialNewsDestinations.AUTHENTICATION_ROUTE) {
            Authentication()
        }
        composable(SocialNewsDestinations.NEWS_LINE_ROUTE) {
            NewsLine()
        }
        composable(SocialNewsDestinations.CREATE_NEWS_ROUTE) {
            CreateNews()
        }
        composable(SocialNewsDestinations.USER_INFO_ROUTE) {
            User()
        }
    }
}

