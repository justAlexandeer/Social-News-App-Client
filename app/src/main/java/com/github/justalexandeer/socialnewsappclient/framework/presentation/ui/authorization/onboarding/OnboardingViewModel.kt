package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.authorization.onboarding

import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding.CheckIsFirstStartAppUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding.GetOnboardingPagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase,
    private val checkIsFirstStartAppUseCase: CheckIsFirstStartAppUseCase
) : ViewModel() {

    val listOfOnboardingPages = getOnboardingPagesUseCase()
    val isFirstStartApp = checkIsFirstStartAppUseCase()

}