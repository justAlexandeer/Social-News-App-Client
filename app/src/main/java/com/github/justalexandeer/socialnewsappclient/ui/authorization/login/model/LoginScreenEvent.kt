package com.github.justalexandeer.socialnewsappclient.ui.authorization.login.model

sealed class LoginScreenEvent {
    data class UserNameTextFieldChange(val text: String): LoginScreenEvent()
    object OnCustomButtonClick: LoginScreenEvent()
}
sealed class PasswordTextFieldEvent: LoginScreenEvent() {
    data class PasswordTextFieldChange(val text: String): LoginScreenEvent()
    object PasswordTextFieldIconClick: LoginScreenEvent()
}