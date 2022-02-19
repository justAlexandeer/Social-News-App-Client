package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.model

sealed class RegistrationScreenEvent {
    data class UserNameTextFieldChange(val text: String): RegistrationScreenEvent()
    data class NameTextFieldChange(val text: String): RegistrationScreenEvent()
    sealed class PasswordTextFieldEvent: RegistrationScreenEvent() {
        data class PasswordTextFieldChange(val text: String): PasswordTextFieldEvent()
        object PasswordTextFieldIconClick: PasswordTextFieldEvent()
    }
    sealed class RepeatPasswordTextFieldEvent: RegistrationScreenEvent() {
        data class RepeatPasswordTextFieldChange(val text: String): RepeatPasswordTextFieldEvent()
        object RepeatPasswordTextFieldIconClick: RepeatPasswordTextFieldEvent()
    }
    object ShowPasswordRequirements: RegistrationScreenEvent()
    object OnCustomButtonClick: RegistrationScreenEvent()
    object ResetRegistrationScreenState: RegistrationScreenEvent()
}