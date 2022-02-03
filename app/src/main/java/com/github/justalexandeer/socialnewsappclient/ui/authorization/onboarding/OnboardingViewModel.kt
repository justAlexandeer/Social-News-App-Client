package com.github.justalexandeer.socialnewsappclient.ui.authorization.onboarding

import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.data.repository.OnboardingPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingPageRepository: OnboardingPageRepository
) : ViewModel() {

    val listOfOnboardingPages = onboardingPageRepository.getOnboardingPages()

}