package com.github.justalexandeer.socialnewsappclient.ui.authorization.registration

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.ui.authorization.AuthorizationNavigation
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.*
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.NameTextField
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.PasswordRequirements
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component.SuccessRegistrationAlertDialog
import com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.model.*

@ExperimentalAnimationGraphicsApi
@Composable
fun RegistrationScreen(
    registrationViewModel: RegistrationViewModel = hiltViewModel(),
    navigation: AuthorizationNavigation
) {

    RegistrationContent(
        registrationViewModel.registrationScreenState.value,
        { registrationViewModel.obtainEvent(it) },
        navigation
    )
}

@ExperimentalAnimationGraphicsApi
@Composable
fun RegistrationContent(
    currentState: RegistrationScreenState,
    sendEvent: (RegistrationScreenEvent) -> Unit,
    navigation: AuthorizationNavigation
) {

    if (currentState.isRegistrationSuccess) {
        SuccessRegistrationAlertDialog(
            true,
            { sendEvent(RegistrationScreenEvent.ResetRegistrationScreenState) },
            { navigation.navigateToLoginScreen() }
        )
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn {
                item {
                    TitleText(
                        stringResource(R.string.text_title_registration),
                        Modifier.padding(vertical = dimensionResource(R.dimen.grid_1))
                    )
                    SubTitleText(stringResource(R.string.text_subtitle_registration))
                    LoginTextField(
                        loginTextFieldState = currentState.loginTextFieldState,
                        userNameTextFieldChange = {
                            sendEvent(
                                RegistrationScreenEvent.UserNameTextFieldChange(
                                    it
                                )
                            )
                        },
                        Modifier
                            .padding(top = dimensionResource(R.dimen.grid_3_5))
                            .padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    NameTextField(
                        nameTextFieldState = currentState.nameTextFieldState,
                        nameTextFieldChange = {
                            sendEvent(
                                RegistrationScreenEvent.NameTextFieldChange(
                                    it
                                )
                            )
                        },
                        Modifier.padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    PasswordTextField(
                        passwordTextFieldState = currentState.passwordTextFieldState,
                        passwordTextFieldChange = {
                            sendEvent(
                                PasswordTextFieldEvent.PasswordTextFieldChange(
                                    it
                                )
                            )
                        },
                        passwordTextFieldIconClick = { sendEvent(PasswordTextFieldEvent.PasswordTextFieldIconClick) },
                        labelText = stringResource(R.string.password),
                        Modifier
                            .padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    PasswordTextField(
                        passwordTextFieldState = currentState.repeatPasswordTextFieldState,
                        passwordTextFieldChange = {
                            sendEvent(
                                RepeatPasswordTextFieldEvent.RepeatPasswordTextFieldChange(
                                    it
                                )
                            )
                        },
                        passwordTextFieldIconClick = { sendEvent(RepeatPasswordTextFieldEvent.RepeatPasswordTextFieldIconClick) },
                        labelText = stringResource(R.string.password_repeat),
                        Modifier
                            .padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    ProgressIndicator(
                        circularProgressIndicatorState = currentState.circularProgressIndicatorState,
                        Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.grid_1))
                    )
                    ErrorSignUpText(
                        errorSignUpTextState = currentState.errorSignUpTextState,
                        Modifier.padding(top = dimensionResource(R.dimen.grid_1))
                    )
                    PasswordRequirements(
                        passwordRequirementsState = currentState.passwordRequirementsState,
                        passwordRequirementsShowClick = { sendEvent(RegistrationScreenEvent.ShowPasswordRequirements) },
                        Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.grid_1))
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            HaveAccountText(onTextClick = { navigation.navigateToLoginScreen() })
            CustomButton(
                currentState.customButtonState,
                { sendEvent(RegistrationScreenEvent.OnCustomButtonClick) },
                stringResource(R.string.button_sign_up)
            )
        }
    }
}

@Composable
fun HaveAccountText(onTextClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.grid_2)
            )
    ) {
        Text(text = stringResource(R.string.textHaveAccount))
        Text(text = " ")
        Text(
            text = stringResource(R.string.login),
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                onTextClick()
            }
        )
    }
}

@Composable
fun ErrorSignUpText(
    errorSignUpTextState: ErrorSignUpTextState,
    modifier: Modifier = Modifier
) {
    if (errorSignUpTextState.isVisible) {
        Text(
            text = errorSignUpTextState.errorMessage,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}
