package com.github.justalexandeer.socialnewsappclient.ui.authorization.registration

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import com.github.justalexandeer.socialnewsappclient.data.repository.RegisterRepository
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.CircularProgressIndicatorState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.CustomButtonState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.LoginTextFieldState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.PasswordTextFieldState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.NameTextFieldState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.PasswordRequirementsState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.RequirementState
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.isValid
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val registerRepository: RegisterRepository
) : ViewModel() {
    private val _registrationScreenState: MutableState<RegistrationScreenState> = mutableStateOf(
        initIdleScreenState()
    )
    val registrationScreenState: MutableState<RegistrationScreenState> = _registrationScreenState

    fun obtainEvent(event: RegistrationScreenEvent) {
        when (event) {
            is PasswordTextFieldEvent.PasswordTextFieldChange -> {
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
                obtainCustomButtonState()
            }
            PasswordTextFieldEvent.PasswordTextFieldIconClick -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    passwordTextFieldState = _registrationScreenState.value.passwordTextFieldState.copy(
                        passwordVisibility = !registrationScreenState.value.passwordTextFieldState.passwordVisibility
                    )
                )
            }
            RepeatPasswordTextFieldEvent.RepeatPasswordTextFieldIconClick -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    repeatPasswordTextFieldState = _registrationScreenState.value.repeatPasswordTextFieldState.copy(
                        passwordVisibility = !registrationScreenState.value.repeatPasswordTextFieldState.passwordVisibility
                    )
                )
            }
            is RepeatPasswordTextFieldEvent.RepeatPasswordTextFieldChange -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    repeatPasswordTextFieldState = _registrationScreenState.value.repeatPasswordTextFieldState.copy(
                        text = event.text
                    )
                )
            }
            RegistrationScreenEvent.ShowPasswordRequirements -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    passwordRequirementsState = _registrationScreenState.value.passwordRequirementsState.copy(
                        isVisible = !_registrationScreenState.value.passwordRequirementsState.isVisible
                    )
                )
            }
            is RegistrationScreenEvent.UserNameTextFieldChange -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    loginTextFieldState = _registrationScreenState.value.loginTextFieldState.copy(
                        text = event.text
                    )
                )
            }
            RegistrationScreenEvent.OnCustomButtonClick -> {
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
                            obtainTextErrorState(
                                context.resources.getString(R.string.name_text_field_must_be_not_empty),
                                true
                            )
                        }
                    } else {
                        obtainTextErrorState(
                            context.resources.getString(R.string.username_text_field_must_be_not_empty),
                            true
                        )
                    }
                } else {
                    obtainTextErrorState(
                        context.resources.getString(R.string.password_are_not_the_same),
                        true
                    )
                }
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    isRegistrationSuccess = true
                )
            }
            is RegistrationScreenEvent.NameTextFieldChange -> {
                _registrationScreenState.value = _registrationScreenState.value.copy(
                    nameTextFieldState = _registrationScreenState.value.nameTextFieldState.copy(
                        text = event.text
                    )
                )
            }
            RegistrationScreenEvent.ResetRegistrationScreenState -> {
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
        obtainTextErrorState("", false)
        registerRepository
            .registerUser(userName, name, password)
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    if (it.status == Response.SUCCESS) {
                        _registrationScreenState.value = _registrationScreenState.value.copy(
                            isRegistrationSuccess = true
                        )
                        disableLoadingState()
                    }
                    if (it.status == Response.ERROR) {
                        obtainTextErrorState(it.message!!, true)
                        disableLoadingState()
                    }
                },
                {
                    if (it.localizedMessage != null) {
                        obtainTextErrorState(it.localizedMessage, true)
                    } else {
                        obtainTextErrorState(
                            context.resources.getString(R.string.something_went_wrong),
                            true
                        )
                    }
                    disableLoadingState()
                }
            )
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
        obtainCustomButtonState()
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

    private fun obtainCustomButtonState() {
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

    private fun obtainTextErrorState(text: String, isVisible: Boolean) {
        _registrationScreenState.value = _registrationScreenState.value.copy(
            errorSignUpTextState = ErrorSignUpTextState(
                errorMessage = text,
                isVisible = isVisible
            )
        )
    }
}