package com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.model

sealed class RegistrationScreenEvent {
    data class UserNameTextFieldChange(val text: String): RegistrationScreenEvent()
    data class NameTextFieldChange(val text: String): RegistrationScreenEvent()
    object ShowPasswordRequirements: RegistrationScreenEvent()
    object OnCustomButtonClick: RegistrationScreenEvent()
    object ResetRegistrationScreenState: RegistrationScreenEvent()
}

sealed class PasswordTextFieldEvent: RegistrationScreenEvent() {
    data class PasswordTextFieldChange(val text: String): RegistrationScreenEvent()
    object PasswordTextFieldIconClick: RegistrationScreenEvent()
}

sealed class RepeatPasswordTextFieldEvent: RegistrationScreenEvent() {
    data class RepeatPasswordTextFieldChange(val text: String): RegistrationScreenEvent()
    object RepeatPasswordTextFieldIconClick: RegistrationScreenEvent()
}