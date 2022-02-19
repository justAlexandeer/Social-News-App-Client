package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import com.github.justalexandeer.socialnewsappclient.R
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.justalexandeer.socialnewsappclient.framework.presentation.MainActivityViewModel
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.socialnews.SocialNewsDestinations
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.socialnews.SocialNewsNavigation
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationGraphicsApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun SocialNewsAppClient() {
    val mainActivityViewModel: MainActivityViewModel = viewModel()
    val needAuthorization by remember { mainActivityViewModel.needAuthorization }

    val navController = rememberNavController()
    val (bottomNavigationVisibility, setBottomNavigationVisibility) = remember { mutableStateOf(!needAuthorization)}

    Scaffold(
        bottomBar = {
            if (bottomNavigationVisibility) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val socialNewsNavigation = remember { SocialNewsNavigation(navController) }
                BottomNavigation {
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Article, contentDescription = null) },
                        label = { Text(stringResource(R.string.news_line_label)) },
                        selected = currentDestination?.hierarchy?.any { it.route == SocialNewsDestinations.NEWS_LINE_ROUTE } == true,
                        onClick = { socialNewsNavigation.navigateToNewsLine() },
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Edit, contentDescription = null) },
                        label = { Text(stringResource(R.string.create_news_label)) },
                        selected = currentDestination?.hierarchy?.any { it.route == SocialNewsDestinations.CREATE_NEWS_ROUTE } == true,
                        onClick = { socialNewsNavigation.navigateToCreateNews() },
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
                        label = { Text(stringResource(R.string.user_info_label)) },
                        selected = currentDestination?.hierarchy?.any { it.route == SocialNewsDestinations.USER_INFO_ROUTE } == true,
                        onClick = { socialNewsNavigation.navigateToUserInfo() },
                    )
                }
            }
        }
    ) { contentPadding ->
        Box {
            SocialNewsAppClientNavGraph(
                needAuthorization = needAuthorization,
                navController = navController,
                setBottomNavigationVisibility = { setBottomNavigationVisibility(it) }
            )
        }

    }
}