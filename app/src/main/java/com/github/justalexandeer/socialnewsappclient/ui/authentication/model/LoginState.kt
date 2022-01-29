package com.github.justalexandeer.socialnewsappclient.ui.authentication.model

sealed class LoginState {
    object Idle: LoginState()
    object Loading: LoginState()
    data class ErrorSignIn(val errorMessage: String): LoginState()
    object SuccessSignIn: LoginState()
}
