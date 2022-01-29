package com.github.justalexandeer.socialnewsappclient.ui.authentication.model

sealed class LoginEvent {
    object ResetStateEvent : LoginEvent()
    data class SignInEvent(val userName: String, val password: String) : LoginEvent()
}