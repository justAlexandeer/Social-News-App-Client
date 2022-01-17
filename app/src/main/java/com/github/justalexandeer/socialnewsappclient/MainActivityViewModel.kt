package com.github.justalexandeer.socialnewsappclient

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.data.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val tokenRepository: TokenRepository
): ViewModel() {

    val needAuthentication = mutableStateOf(!tokenRepository.getIsUserAuthenticated())

    fun changeValueAuthorization(flag: Boolean) {
        tokenRepository.setAuthenticationFlag(flag)
        needAuthentication.value = (!tokenRepository.getIsUserAuthenticated())
        Log.i("NewsLineViewModelTEST", needAuthentication.value.toString())
    }

}