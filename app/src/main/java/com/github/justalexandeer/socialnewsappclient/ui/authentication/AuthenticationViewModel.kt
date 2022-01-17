package com.github.justalexandeer.socialnewsappclient.ui.authentication

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.data.repository.LoginRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPageRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.RegisterRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val registrationRepository: RegisterRepository,
    private val tokenRepository: TokenRepository,
    private val onboardingPageRepository: OnboardingPageRepository
): ViewModel() {

    val isLogging = mutableStateOf(false)
    val listOfOnboardingPages = onboardingPageRepository.getOnboardingPages()

    fun login(userName: String, password: String) {
        loginRepository
            .loginUser(userName, password)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    Log.i(TAG, "login: ${it.status}")
                    Log.i(TAG, "login: ${it.message}")
                    if (it.data != null) {
                        tokenRepository.setAccessToken(it.data.access_token)
                        tokenRepository.setRefreshToken(it.data.refresh_token)
                        isLogging.value = true
                    }
                },
                {

                }
            )
    }

    fun registration(userName: String, name: String, password: String) {
        registrationRepository
            .registerUser(userName, name, password)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    Log.i(TAG, "registration: ${it.status}")
                    Log.i(TAG, "registration: ${it.message}")
                },
                {

                }
            )
    }

    companion object {
        private const val TAG = "AuthenticationViewModel"
    }
}