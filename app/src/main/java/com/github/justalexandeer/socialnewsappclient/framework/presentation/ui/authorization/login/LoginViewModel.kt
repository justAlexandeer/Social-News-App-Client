package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import com.github.justalexandeer.socialnewsappclient.business.interactors.login.LoginUseCase
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.CircularProgressIndicatorState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.CustomButtonState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.login.model.ErrorSignInTextState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.login.model.LoginScreenEvent
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.login.model.LoginScreenState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.login.model.PasswordTextFieldEvent
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.LoginTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.PasswordTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
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
        viewModelScope.launch {
            loginUseCase.invoke(userName, password).collect {
                when(it.status) {
                    is DataStateStatus.Success -> {
                        _loginViewState.value = _loginViewState.value.copy(
                            isLoginSuccess = true
                        )
                        disableLoadingState()
                    }
                    is DataStateStatus.Error -> {
                        _loginViewState.value = _loginViewState.value.copy(
                            errorSignInTextState = ErrorSignInTextState(
                                errorMessage = it.errorMessage!!,
                                isVisible = true
                            )
                        )
                        disableLoadingState()
                    }
                }
            }
        }
    }

    private fun disableLoadingState() {
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