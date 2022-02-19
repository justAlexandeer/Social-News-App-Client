package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import com.github.justalexandeer.socialnewsappclient.business.interactors.registration.RegisterUseCase
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.CircularProgressIndicatorState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.CustomButtonState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.LoginTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.component.PasswordTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component.NameTextFieldState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component.PasswordRequirementsState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component.RequirementState
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component.isValid
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _registrationScreenState: MutableState<RegistrationScreenState> = mutableStateOf(
        initIdleScreenState()
    )
    val registrationScreenState: MutableState<RegistrationScreenState> = _registrationScreenState

    fun obtainEvent(event: RegistrationScreenEvent) {
        when (event) {
            is RegistrationScreenEvent.PasswordTextFieldEvent -> {
                handlePasswordTextFieldEvent(event)
            }
            is RegistrationScreenEvent.RepeatPasswordTextFieldEvent -> {
                handleRepeatPasswordTextFieldEvent(event)
            }
            is RegistrationScreenEvent.ShowPasswordRequirements -> {
                handlePasswordRequirementsStatesVisibility()
            }
            is RegistrationScreenEvent.UserNameTextFieldChange -> {
                handleUserNameTextFieldChangeEvent(event)
            }
            is RegistrationScreenEvent.OnCustomButtonClick -> {
                handleOnCustomButtonClickEvent()
            }
            is RegistrationScreenEvent.NameTextFieldChange -> {
                handleNameTextFieldChangeEvent(event)
            }
            is RegistrationScreenEvent.ResetRegistrationScreenState -> {
                _registrationScreenState.value = initIdleScreenState()
            }
        }
    }

    fun registration(userName: String, name: String, password: String) {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            circularProgressIndicatorState = CircularProgressIndicatorState(true)
        )
        _registrationScreenState.value = _registrationScreenState.value.copy(
            customButtonState = CustomButtonState(false)
        )
        handleTextErrorState("", false)

        viewModelScope.launch {
            registerUseCase.invoke(userName, name, password).collect {
                when(it.status) {
                    DataStateStatus.Success -> {
                        _registrationScreenState.value = _registrationScreenState.value.copy(
                            isRegistrationSuccess = true
                        )
                        disableLoadingState()
                    }
                    DataStateStatus.Error -> {
                        handleTextErrorState(it.errorMessage!!, true)
                        disableLoadingState()
                    }
                }
            }
        }
    }

    private fun initIdleScreenState(): RegistrationScreenState {
        return RegistrationScreenState(
            LoginTextFieldState(""),
            NameTextFieldState(""),
            PasswordTextFieldState("", false),
            PasswordTextFieldState("", false),
            CustomButtonState(false),
            CircularProgressIndicatorState(false),
            ErrorSignUpTextState("", false),
            PasswordRequirementsState(
                false, listOf(
                    RequirementState(
                        ".{8,}",
                        context.resources.getString(R.string.password_requirement_min_characters),
                        false
                    ),
                    RequirementState(
                        ".*[a-zA-z].*",
                        context.resources.getString(R.string.password_requirement_only_latin_letters),
                        false
                    ),
                    RequirementState(
                        ".*[A-Z].*",
                        context.resources.getString(R.string.password_requirement_one_capital),
                        false
                    ),
                    RequirementState(
                        ".*[!@#%^&].*",
                        context.resources.getString(R.string.password_requirement_one_special_character),
                        false
                    )
                )
            ),
            false
        )
    }

    private fun disableLoadingState() {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            circularProgressIndicatorState = CircularProgressIndicatorState(false)
        )
        handleCustomButtonState()
    }

    private fun handlePasswordRequirementsStates(
        updateText: String,
        listOfRequirement: List<RequirementState>
    ): List<RequirementState> {
        val newListOfRequirementState = mutableListOf<RequirementState>()
        listOfRequirement.forEach {
            newListOfRequirementState.add(
                RequirementState(
                    it.validationRegex,
                    it.text,
                    isValid(updateText, it.validationRegex)
                )
            )
        }
        return newListOfRequirementState
    }

    private fun handlePasswordTextFieldEvent(event: RegistrationScreenEvent.PasswordTextFieldEvent) {
        when(event) {
            is RegistrationScreenEvent.PasswordTextFieldEvent.PasswordTextFieldChange -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    passwordRequirementsState = _registrationScreenState.value.passwordRequirementsState.copy(
                        list = handlePasswordRequirementsStates(
                            event.text,
                            _registrationScreenState.value.passwordRequirementsState.list
                        )
                    )
                )
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    passwordTextFieldState = _registrationScreenState.value.passwordTextFieldState.copy(
                        text = event.text
                    )
                )
                handleCustomButtonState()
            }
            is RegistrationScreenEvent.PasswordTextFieldEvent.PasswordTextFieldIconClick -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    passwordTextFieldState = _registrationScreenState.value.passwordTextFieldState.copy(
                        passwordVisibility = !registrationScreenState.value.passwordTextFieldState.passwordVisibility
                    )
                )
            }
        }
    }

    private fun handleRepeatPasswordTextFieldEvent(event: RegistrationScreenEvent.RepeatPasswordTextFieldEvent) {
        when(event) {
            is RegistrationScreenEvent.RepeatPasswordTextFieldEvent.RepeatPasswordTextFieldIconClick -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    repeatPasswordTextFieldState = _registrationScreenState.value.repeatPasswordTextFieldState.copy(
                        passwordVisibility = !registrationScreenState.value.repeatPasswordTextFieldState.passwordVisibility
                    )
                )
            }
            is RegistrationScreenEvent.RepeatPasswordTextFieldEvent.RepeatPasswordTextFieldChange -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    repeatPasswordTextFieldState = _registrationScreenState.value.repeatPasswordTextFieldState.copy(
                        text = event.text
                    )
                )
            }
        }
    }

    private fun handlePasswordRequirementsStatesVisibility() {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            passwordRequirementsState = _registrationScreenState.value.passwordRequirementsState.copy(
                isVisible = !_registrationScreenState.value.passwordRequirementsState.isVisible
            )
        )
    }

    private fun handleUserNameTextFieldChangeEvent(event: RegistrationScreenEvent.UserNameTextFieldChange) {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            loginTextFieldState = _registrationScreenState.value.loginTextFieldState.copy(
                text = event.text
            )
        )
    }

    private fun handleNameTextFieldChangeEvent(event: RegistrationScreenEvent.NameTextFieldChange) {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            nameTextFieldState = _registrationScreenState.value.nameTextFieldState.copy(
                text = event.text
            )
        )
    }

    private fun handleCustomButtonState() {
        if (_registrationScreenState.value.passwordRequirementsState.list.all {
                isValid(
                    _registrationScreenState.value.passwordTextFieldState.text,
                    it.validationRegex
                )
            }) {
            _registrationScreenState.value = _registrationScreenState.value.copy(
                customButtonState = CustomButtonState(true)
            )
        } else {
            _registrationScreenState.value = _registrationScreenState.value.copy(
                customButtonState = CustomButtonState(false)
            )
        }
    }

    private fun handleOnCustomButtonClickEvent() {
        if (_registrationScreenState.value.passwordTextFieldState.text ==
            _registrationScreenState.value.repeatPasswordTextFieldState.text
        ) {
            if (_registrationScreenState.value.loginTextFieldState.text.isNotBlank()) {
                if (_registrationScreenState.value.nameTextFieldState.text.isNotBlank()) {
                    registration(
                        _registrationScreenState.value.loginTextFieldState.text,
                        _registrationScreenState.value.nameTextFieldState.text,
                        _registrationScreenState.value.passwordTextFieldState.text
                    )
                } else {
                    handleTextErrorState(
                        context.resources.getString(R.string.name_text_field_must_be_not_empty),
                        true
                    )
                }
            } else {
                handleTextErrorState(
                    context.resources.getString(R.string.username_text_field_must_be_not_empty),
                    true
                )
            }
        } else {
            handleTextErrorState(
                context.resources.getString(R.string.password_are_not_the_same),
                true
            )
        }
    }

    private fun handleTextErrorState(text: String, isVisible: Boolean) {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            errorSignUpTextState = ErrorSignUpTextState(
                errorMessage = text,
                isVisible = isVisible
            )
        )
    }
}