package com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.AuthorizationLocalRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckIsFirstStartAppUseCaseImpl @Inject constructor(
    private val authorizationLocalRepository: AuthorizationLocalRepository
) : CheckIsFirstStartAppUseCase {

    override operator fun invoke(): Boolean {
        return authorizationLocalRepository.getFirstStartAppFlag()
    }

}