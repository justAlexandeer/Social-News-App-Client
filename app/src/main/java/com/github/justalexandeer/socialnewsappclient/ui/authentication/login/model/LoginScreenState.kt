package com.github.justalexandeer.socialnewsappclient.ui.authentication.login.model

import org.w3c.dom.Text

data class LoginScreenState (
    val loginTextFieldState: LoginTextFieldState,
    val passwordTextFieldState: PasswordTextFieldState,
    val customButtonState: CustomButtonState,
    val circularProgressIndicatorState: CircularProgressIndicatorState,
    val errorSignInTextState: ErrorSignInTextState,
    val isLoginSuccess: Boolean
)

data class LoginTextFieldState(
    val text: String
)

data class PasswordTextFieldState(
    val text: String,
    val passwordVisibility: Boolean,
)

data class CustomButtonState(
    val isEnabled: Boolean
)

data class CircularProgressIndicatorState(
    val isVisible: Boolean
)

data class ErrorSignInTextState(
    val errorMessage: String,
    val isVisible: Boolean
)