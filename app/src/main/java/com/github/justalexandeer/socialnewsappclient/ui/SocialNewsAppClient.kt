package com.github.justalexandeer.socialnewsappclient.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.justalexandeer.socialnewsappclient.R

@Composable
fun SocialNewsAppClient() {
    val navController = rememberNavController()
    val navigationActions = remember(navController) { SocialNewsNavigation(navController) }
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Article, contentDescription = null) },
                    label = { Text(stringResource(R.string.news_line_label)) },
                    selected = currentDestination?.hierarchy?.any { it.route == SocialNewsDestinations.NEWS_LINE_ROUTE } == true,
                    onClick = { navigationActions.navigateToNewsLine() },
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Edit, contentDescription = null) },
                    label = { Text(stringResource(R.string.create_news_label)) },
                    selected = currentDestination?.hierarchy?.any { it.route == SocialNewsDestinations.CREATE_NEWS_ROUTE } == true,
                    onClick = { navigationActions.navigateToCreateNews() },
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.AccountCircle, contentDescription = null) },
                    label = { Text(stringResource(R.string.user_info_label)) },
                    selected = currentDestination?.hierarchy?.any { it.route == SocialNewsDestinations.USER_INFO_ROUTE } == true,
                    onClick = { navigationActions.navigateToUserInfo() },
                )
            }
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            SocialNewsNavGraph(
                navController = navController
            )
        }
    }
}

private const val TAG = "SocialNewsAppClient"