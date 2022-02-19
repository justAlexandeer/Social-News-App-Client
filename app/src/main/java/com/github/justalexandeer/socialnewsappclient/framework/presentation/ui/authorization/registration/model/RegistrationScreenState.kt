package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.model

import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.CircularProgressIndicatorState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.CustomButtonState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.LoginTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.PasswordTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component.NameTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component.PasswordRequirementsState

data class RegistrationScreenState(
    val loginTextFieldState: LoginTextFieldState,
    val nameTextFieldState: NameTextFieldState,
    val passwordTextFieldState: PasswordTextFieldState,
    val repeatPasswordTextFieldState: PasswordTextFieldState,
    val customButtonState: CustomButtonState,
    val circularProgressIndicatorState: CircularProgressIndicatorState,
    val errorSignUpTextState: ErrorSignUpTextState,
    val passwordRequirementsState: PasswordRequirementsState,
    val isRegistrationSuccess: Boolean
)

data class ErrorSignUpTextState(
    val errorMessage: String,
    val isVisible: Boolean
)



