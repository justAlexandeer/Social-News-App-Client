package com.github.justalexandeer.socialnewsappclient.ui.authentication.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import com.github.justalexandeer.socialnewsappclient.data.repository.LoginRepository
import com.github.justalexandeer.socialnewsappclient.data.repository.TokenRepository
import com.github.justalexandeer.socialnewsappclient.ui.authentication.login.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : ViewModel() {

    private val _loginViewState: MutableState<LoginScreenState> = mutableStateOf(
        LoginScreenState(
            LoginTextFieldState(""),
            PasswordTextFieldState("", false),
            CustomButtonState(true),
            CircularProgressIndicatorState(false),
            ErrorSignInTextState("", false),
            false
        )
    )
    val loginViewState: MutableState<LoginScreenState> = _loginViewState

    fun obtainEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.UserNameTextFieldChange -> {
                _loginViewState.value = _loginViewState.value.copy(
                    loginTextFieldState = LoginTextFieldState(text = event.text)
                )
            }
            is PasswordTextFieldEvent.PasswordTextFieldChange -> {
                _loginViewState.value = _loginViewState.value.copy(
                    passwordTextFieldState = _loginViewState.value.passwordTextFieldState.copy(
                        text = event.text
                    )
                )
            }
            PasswordTextFieldEvent.PasswordTextFieldIconClick -> {
                _loginViewState.value = _loginViewState.value.copy(
                    passwordTextFieldState = _loginViewState.value.passwordTextFieldState.copy(
                        passwordVisibility = !loginViewState.value.passwordTextFieldState.passwordVisibility
                    )
                )
            }
            LoginScreenEvent.OnCustomButtonClick -> {
                login(
                    _loginViewState.value.loginTextFieldState.text,
                    _loginViewState.value.passwordTextFieldState.text
                )
            }
        }
    }

    private fun login(userName: String, password: String) {
        _loginViewState.value = _loginViewState.value.copy(
            circularProgressIndicatorState = CircularProgressIndicatorState(true)
        )
        _loginViewState.value = _loginViewState.value.copy(
            customButtonState = CustomButtonState(false)
        )
        _loginViewState.value = _loginViewState.value.copy(
            errorSignInTextState = ErrorSignInTextState(
                isVisible = false,
                errorMessage = ""
            )
        )
        loginRepository
            .loginUser(userName, password)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    if (it.status == Response.SUCCESS) {
                        tokenRepository.setAccessToken(it.data!!.access_token)
                        tokenRepository.setRefreshToken(it.data.refresh_token)
                        tokenRepository.setAuthenticationFlag(true)
                        _loginViewState.value = _loginViewState.value.copy(
                            isLoginSuccess = true
                        )
                        disableLoadingState()
                    }
                    if (it.status == Response.ERROR) {
                        _loginViewState.value = _loginViewState.value.copy(
                            errorSignInTextState = ErrorSignInTextState(
                                errorMessage = it.message!!,
                                isVisible = true
                            )
                        )
                        disableLoadingState()
                    }
                },
                {
                    it.localizedMessage?.let { errorMessage ->
                        _loginViewState.value = _loginViewState.value.copy(
                            errorSignInTextState = ErrorSignInTextState(
                                errorMessage = errorMessage,
                                isVisible = true
                            )
                        )
                    }
                    disableLoadingState()
                }
            )
    }

    fun disableLoadingState() {
        _loginViewState.value = _loginViewState.value.copy(
            circularProgressIndicatorState = CircularProgressIndicatorState(false)
        )
        _loginViewState.value = _loginViewState.value.copy(
            customButtonState = CustomButtonState(true)
        )
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }
}