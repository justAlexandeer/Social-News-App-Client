package com.github.justalexandeer.socialnewsappclient.ui.authentication

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.LoginTextField
import com.github.justalexandeer.socialnewsappclient.ui.authentication.component.PasswordTextField

@Composable
fun RegistrationScreen() {
    RegistrationContent()
}

@Composable
fun RegistrationContent() {
    val (textUserName, setUserName) = remember { mutableStateOf("") }
    val (textPassword, setPassword) = remember { mutableStateOf("") }
    var (isExpanded, setIsExpanded) = remember { mutableStateOf(false) }

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
//                    LoginTextField(textUserName = textUserName, setTextUserName = setUserName)
//                    PasswordTextField(textPassword = textPassword, setTextPassword = setPassword)
                    ShowPasswordRequirementsText(
                        isExpanded,
                        setIsExpanded
                    )
                    Column(
                        Modifier
                            .animateContentSize()
                            .fillMaxWidth()
                    ) {
                        if (isExpanded) {
                            PasswordRequirements(textPassword)
                        }
                    }
                }
            }

        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

        }
    }
}

@Composable
fun ShowPasswordRequirementsText(
    isExpanded: Boolean,
    setIsExpanded: (Boolean) -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = if (!isExpanded) "Показать больше" else "Показать меньше",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    setIsExpanded(!isExpanded)
                }
        )
    }
}

@Composable
fun PasswordRequirements(
    textPassword: String
) {
    Text(
        text = "Длина допустим больше 6",
        color = if (textPassword.length > 6) Color.Green else Color.Red,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = "Другие требования",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = "Другие требования",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = "Другие требования",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

/*

    1. Пароль должен содержать больше 7 символов
    2. Содержать только латинские буквы
    3. Минимум одну заглавную букву
    4. Минимум один специ символ *,.!$%^

 */