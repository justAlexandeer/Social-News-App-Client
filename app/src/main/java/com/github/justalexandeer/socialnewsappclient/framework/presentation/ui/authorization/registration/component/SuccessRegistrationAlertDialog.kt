package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.registration.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.github.justalexandeer.socialnewsappclient.R
import kotlinx.coroutines.delay

@Composable
fun SuccessRegistrationAlertDialog(
    isVisible: Boolean,
    onDismissButtonClick: () -> Unit,
    onSignInButtonClick: () -> Unit
) {
    val openDialog = remember { mutableStateOf(isVisible) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(
                    text = stringResource(R.string.title_alert_dialog_success_registration),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.text_alert_dialog_success_registration),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.size(225.dp)
                    ) {
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animated_vector_success))
                        var isPlaying by remember { mutableStateOf(true) }
                        val progress by animateLottieCompositionAsState(
                            composition = composition,
                            isPlaying = isPlaying,
                            iterations = LottieConstants.IterateForever
                        )
                        LottieAnimation(composition, progress)
                        if (progress == 1f) {
                            isPlaying = false
                            LaunchedEffect(openDialog) {
                                delay(3000L)
                                isPlaying = true
                            }
                        }
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissButtonClick()
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(R.string.dismiss_text_button))
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onSignInButtonClick()
                        openDialog.value = false
                    }
                ) {
                    Text(stringResource(R.string.button_sign_in))
                }
            }
        )
    }
}
