package com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding

import com.github.justalexandeer.socialnewsappclient.business.domain.model.OnboardingPage

interface GetOnboardingPagesUseCase {
    operator fun invoke(): List<OnboardingPage>
}