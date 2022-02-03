package com.github.justalexandeer.socialnewsappclient.ui.authorization.registration.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.justalexandeer.socialnewsappclient.R
import java.util.regex.Pattern

@ExperimentalAnimationGraphicsApi
@Composable
fun PasswordRequirements(
    passwordRequirementsState: PasswordRequirementsState,
    passwordRequirementsShowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(bottom = dimensionResource(R.dimen.grid_0_5))
    ) {
        val arrowImage = animatedVectorResource(R.drawable.avd_arrow)
        Icon(
            painter = arrowImage.painterFor(passwordRequirementsState.isVisible),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .align(CenterVertically)
        )
        Text(
            text = stringResource(R.string.password_requirements_show),
            Modifier
                .align(CenterVertically)
                .clickable { passwordRequirementsShowClick() }
                .padding(horizontal = dimensionResource(R.dimen.grid_1))
        )
        Icon(
            painter = arrowImage.painterFor(passwordRequirementsState.isVisible),
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .align(CenterVertically)
        )
    }

    Surface(
        elevation = 1.dp,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = dimensionResource(R.dimen.grid_1))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
        ) {
            if (passwordRequirementsState.isVisible) {
                Column(
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.grid_1),
                            bottom = dimensionResource(R.dimen.grid_0_5)
                        )
                ) {
                    passwordRequirementsState.list.forEach {
                        Row(
                            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.grid_0_5))
                        ) {
                            val image = animatedVectorResource(R.drawable.avd_close_to_check)
                            val color = remember { Animatable(Color.Gray) }
                            LaunchedEffect(it.isValid) {
                                color.animateTo(
                                    targetValue = if (it.isValid) Color.Green else Color.Red,
                                    animationSpec = TweenSpec(1000)
                                )
                            }
                            Icon(
                                painter = image.painterFor(it.isValid),
                                contentDescription = null,
                                tint = color.value,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = dimensionResource(R.dimen.grid_0_5))
                                    .align(CenterVertically)
                            )
                            Text(
                                text = it.text,
                                Modifier.align(CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }

}

data class PasswordRequirementsState(
    val isVisible: Boolean,
    val list: List<RequirementState>
)

data class RequirementState(
    val validationRegex: String,
    val text: String,
    val isValid: Boolean
)

fun isValid(password: String, validationRegex: String): Boolean {
    return Pattern.matches(validationRegex, password)
}
