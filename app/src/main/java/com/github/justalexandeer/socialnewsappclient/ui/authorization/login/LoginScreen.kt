package com.github.justalexandeer.socialnewsappclient.ui.authorization.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.ui.authorization.AuthorizationNavigation
import com.github.justalexandeer.socialnewsappclient.ui.authorization.component.*
import com.github.justalexandeer.socialnewsappclient.ui.authorization.login.model.*

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigation: AuthorizationNavigation
) {
    val currentState = loginViewModel.loginViewState

    LoginContent(
        currentState.value,
        { loginViewModel.obtainEvent(it) },
        navigation
    )
}

@Composable
fun LoginContent(
    currentState: LoginScreenState,
    sendEvent: (LoginScreenEvent) -> Unit,
    navigation: AuthorizationNavigation
) {

    if(currentState.isLoginSuccess) {
        navigation.navigateToSocialMediaGraph()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.grid_2))
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn {
                item {
                    TitleText(
                        stringResource(R.string.text_title_login),
                        Modifier.padding(vertical = dimensionResource(R.dimen.grid_1))
                    )
                    SubTitleText(stringResource(R.string.text_subtitle_login))
                    LoginTextField(
                        loginTextFieldState = currentState.loginTextFieldState,
                        userNameTextFieldChange = { sendEvent(LoginScreenEvent.UserNameTextFieldChange(it)) },
                        Modifier
                            .padding(top = dimensionResource(R.dimen.grid_3_5))
                            .padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    PasswordTextField(
                        passwordTextFieldState = currentState.passwordTextFieldState,
                        passwordTextFieldChange = { sendEvent(PasswordTextFieldEvent.PasswordTextFieldChange(it)) },
                        passwordTextFieldIconClick = { sendEvent(PasswordTextFieldEvent.PasswordTextFieldIconClick) },
                        labelText = stringResource(R.string.password)
                    )
                    ProgressIndicator(
                        currentState.circularProgressIndicatorState,
                        Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.grid_3))
                    )
                    ErrorSignInText(
                        currentState.errorSignInTextState,
                        Modifier.padding(top = dimensionResource(R.dimen.grid_3))
                    )

                }

            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoAccountText( onTextClick =  { navigation.navigateToRegistrationScreen() })
            CustomButton(
                currentState.customButtonState,
                { sendEvent(LoginScreenEvent.OnCustomButtonClick) },
                stringResource(R.string.button_sign_in)
            )
        }
    }
}

@Composable
fun NoAccountText(onTextClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.grid_2)
            )
    ) {
        Text(text = stringResource(R.string.textNoAccount))
        Text(text = " ")
        Text(
            text = stringResource(R.string.register),
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                onTextClick()
            }
        )
    }
}

@Composable
fun ErrorSignInText(
    errorSignInTextState: ErrorSignInTextState,
    modifier: Modifier = Modifier
) {
    if (errorSignInTextState.isVisible) {
        Text(
            text = errorSignInTextState.errorMessage,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
    }
}
