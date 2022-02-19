package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.OnboardingPage

interface OnboardingPagesLocalRepository {
    fun getOnboardingPages(): List<OnboardingPage>
}