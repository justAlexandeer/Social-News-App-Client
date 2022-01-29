package com.github.justalexandeer.socialnewsappclient.ui.authentication.login

import android.util.Log
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.CustomButton
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.LoginTextField
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.PasswordTextField
import com.github.justalexandeer.socialnewsappclient.ui.authentication.login.model.*

@Composable
fun LoginScreen(
    navigate: @Composable () -> Unit
) {
    val loginViewModel: LoginViewModel = viewModel()
    val currentState = loginViewModel.loginViewState

    LoginContent(
        currentState.value,
        { loginViewModel.obtainEvent(it) },
        navigate
    )
}

@Composable
fun LoginContent(
    currentState: LoginScreenState,
    sendEvent: (LoginScreenEvent) -> Unit,
    navigate: @Composable () -> Unit
) {

    if(currentState.isLoginSuccess) {
        navigate()
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
                    LoginTitle(
                        Modifier
                            .padding(vertical = dimensionResource(R.dimen.grid_1))
                    )
                    SubTitle()
                    LoginTextField(
                        currentState.loginTextFieldState,
                        { sendEvent(LoginScreenEvent.UserNameTextFieldChange(it)) },
                        Modifier
                            .padding(top = dimensionResource(R.dimen.grid_3_5))
                            .padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    PasswordTextField(
                        currentState.passwordTextFieldState,
                        { sendEvent(PasswordTextFieldEvent.PasswordTextFieldChange(it)) },
                        { sendEvent(PasswordTextFieldEvent.PasswordTextFieldIconClick) }
                    )
                    ProgressIndicator(
                        currentState.circularProgressIndicatorState
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
            NoAccountText()
            CustomButton(
                currentState.customButtonState,
                { sendEvent(LoginScreenEvent.OnCustomButtonClick) }
            )
        }
    }
}

@Composable
fun LoginTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.text_title_login),
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun SubTitle() {
    Text(
        text = stringResource(R.string.text_subtitle_login),
        style = MaterialTheme.typography.h5,
        maxLines = 2,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun NoAccountText() {
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

@Composable
fun ProgressIndicator(
    circularProgressIndicatorState: CircularProgressIndicatorState
) {
    if (circularProgressIndicatorState.isVisible) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.grid_3))
        ) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}