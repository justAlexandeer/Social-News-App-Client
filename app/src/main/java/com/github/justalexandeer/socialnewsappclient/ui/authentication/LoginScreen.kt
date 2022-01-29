package com.github.justalexandeer.socialnewsappclient.ui.authentication

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
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.CustomButton
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.LoginTextField
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.PasswordTextField
import com.github.justalexandeer.socialnewsappclient.ui.authentication.model.LoginEvent
import com.github.justalexandeer.socialnewsappclient.ui.authentication.model.LoginState

@Composable
fun LoginScreen(
    authenticationViewModel: AuthenticationViewModel,
    changeAuthenticationFlag: (Boolean) -> Unit
) {
    val currentState = authenticationViewModel.loginViewState
    LoginContent(
        currentState.value,
        { authenticationViewModel.obtainLoginEvent(it) },
        changeAuthenticationFlag
    )
}

@Composable
fun LoginContent(
    currentState: LoginState,
    sendEvent: (LoginEvent) -> Unit,
    changeAuthenticationFlag: (Boolean) -> Unit
) {
    val (textUserName, setUserName) = remember { mutableStateOf("") }
    val (textPassword, setPassword) = remember { mutableStateOf("") }

    if (currentState is LoginState.SuccessSignIn) {
        changeAuthenticationFlag(true)
        sendEvent(LoginEvent.ResetStateEvent)
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
                        textUserName,
                        setUserName,
                        Modifier
                            .padding(top = dimensionResource(R.dimen.grid_3_5))
                            .padding(bottom = dimensionResource(R.dimen.grid_1))
                    )
                    PasswordTextField(
                        textPassword,
                        setPassword
                    )
                    if (currentState is LoginState.Loading) {
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
                    if (currentState is LoginState.ErrorSignIn) {
                        ErrorSignInText(
                            currentState.errorMessage,
                            Modifier.padding(top = dimensionResource(R.dimen.grid_3))
                        )
                    }
                }

            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoAccountText()
            CustomButton(
                currentState !is LoginState.Loading,
                { sendEvent(LoginEvent.SignInEvent(textUserName, textPassword)) }
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
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = errorMessage,
        color = Color.Red,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}