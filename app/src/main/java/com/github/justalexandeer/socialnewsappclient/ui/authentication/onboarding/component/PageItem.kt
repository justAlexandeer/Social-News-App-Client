package com.github.justalexandeer.socialnewsappclient.ui.authentication.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.github.justalexandeer.socialnewsappclient.R
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPage
import com.google.android.material.math.MathUtils

@Composable
fun Page(onboardingPage: OnboardingPage, pageOffset: Float) {
    Column {
        PageImage(
            onboardingPage = onboardingPage,
            pageOffset = pageOffset,
            modifier = Modifier
                .weight(1f)
        )
        PageTitle(
            titleText = onboardingPage.titleText,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.grid_1_5),
                    bottom = dimensionResource(R.dimen.grid_3_5)
                )
                .fillMaxWidth()
        )
        PageDescription(
            descriptionText = onboardingPage.contextText,
            pageOffset = pageOffset,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.grid_3_5))
                .padding(horizontal = dimensionResource(R.dimen.grid_2))
        )
    }
}

@Composable
fun PageImage(
    onboardingPage: OnboardingPage,
    pageOffset: Float,
    color: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = (dimensionResource(R.dimen.grid_2)))
    ) {
        Image(
            painter = painterResource(onboardingPage.resId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color),
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    alpha = MathUtils.lerp(
                        0.0f,
                        1f,
                        1f - (pageOffset.coerceIn(0f, 1f) * 2f)
                    )
                }
        )
    }
}

@Composable
fun PageTitle(
    titleText: String,
    color: Color = MaterialTheme.colors.primary,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = (dimensionResource(R.dimen.grid_2)))
    ) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = color,
            maxLines = 2,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PageDescription(
    descriptionText: String,
    pageOffset: Float,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = descriptionText,
            style = MaterialTheme.typography.body1,
            maxLines = 4,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer {
                    alpha = MathUtils.lerp(
                        0.0f,
                        1f,
                        1f - (pageOffset.coerceIn(0f, 1f) * 1.5f)
                    )
                }
        )
    }
}