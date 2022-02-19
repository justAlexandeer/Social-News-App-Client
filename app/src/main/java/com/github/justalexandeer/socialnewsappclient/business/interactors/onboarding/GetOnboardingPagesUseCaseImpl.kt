package com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.OnboardingPagesLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.OnboardingPage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetOnboardingPagesUseCaseImpl @Inject constructor(
    private val onboardingPagesLocalRepository: OnboardingPagesLocalRepository
) : GetOnboardingPagesUseCase {

    override operator fun invoke(): List<OnboardingPage> {
        return onboardingPagesLocalRepository.getOnboardingPages()
    }

}