package com.github.justalexandeer.socialnewsappclient.ui.authorization.login.model

import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.CircularProgressIndicatorState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.CustomButtonState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.LoginTextFieldState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.PasswordTextFieldState

data class LoginScreenState (
    val loginTextFieldState: LoginTextFieldState,
    val passwordTextFieldState: PasswordTextFieldState,
    val customButtonState: CustomButtonState,
    val circularProgressIndicatorState: CircularProgressIndicatorState,
    val errorSignInTextState: ErrorSignInTextState,
    val isLoginSuccess: Boolean
)

data class ErrorSignInTextState(
    val errorMessage: String,
    val isVisible: Boolean
)