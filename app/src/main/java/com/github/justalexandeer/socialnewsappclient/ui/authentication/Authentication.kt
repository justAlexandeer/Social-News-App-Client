package com.github.justalexandeer.socialnewsappclient.ui.authentication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalPagerApi
@InternalCoroutinesApi
@Composable
fun Authentication(changeAuthenticationFlag: (Boolean) -> Unit) {

    val authenticationViewModel: AuthenticationViewModel = viewModel()

    //OnboardingScreen(authenticationViewModel.listOfOnboardingPages)

}