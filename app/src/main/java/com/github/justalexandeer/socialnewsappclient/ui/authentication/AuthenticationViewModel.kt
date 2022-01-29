package com.github.justalexandeer.socialnewsappclient.ui.authentication

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import com.github.justalexandeer.socialnewsappclient.data.repository.LoginRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPageRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.RegisterRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.TokenRepository
import com.github.justalexandeer.socialnewsappclient.ui.authentication.model.LoginEvent
import com.github.justalexandeer.socialnewsappclient.ui.authentication.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val registrationRepository: RegisterRepository,
    private val tokenRepository: TokenRepository,
    private val onboardingPageRepository: OnboardingPageRepository
): ViewModel() {

    val listOfOnboardingPages = onboardingPageRepository.getOnboardingPages()

    private val _loginViewState: MutableState<LoginState> = mutableStateOf(LoginState.Idle)
    val loginViewState: State<LoginState> = _loginViewState

    fun obtainLoginEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.ResetStateEvent -> {
                _loginViewState.value = LoginState.Idle
            }
            is LoginEvent.SignInEvent -> {
                login(event.userName, event.password)
            }
        }
    }

    private fun login(userName: String, password: String) {
        _loginViewState.value = LoginState.Loading
        loginRepository
            .loginUser(userName, password)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    if (it.status == Response.SUCCESS) {
                        tokenRepository.setAccessToken(it.data!!.access_token)
                        tokenRepository.setRefreshToken(it.data.refresh_token)
                        _loginViewState.value = LoginState.SuccessSignIn
                    }
                    if (it.status == Response.ERROR) {
                        _loginViewState.value = LoginState.ErrorSignIn(it.message!!)
                    }
                },
                {
                    it.localizedMessage?.let { errorMessage ->
                        _loginViewState.value = LoginState.ErrorSignIn(errorMessage)
                    }
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