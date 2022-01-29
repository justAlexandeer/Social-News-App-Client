package com.github.justalexandeer.socialnewsappclient.ui.authentication

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun Authentication(changeAuthenticationFlag: (Boolean) -> Unit) {

    val authenticationViewModel: AuthenticationViewModel = viewModel()

    //OnboardingScreen(authenticationViewModel.listOfOnboardingPages)
    LoginScreen(
        authenticationViewModel,
        changeAuthenticationFlag
    )
}