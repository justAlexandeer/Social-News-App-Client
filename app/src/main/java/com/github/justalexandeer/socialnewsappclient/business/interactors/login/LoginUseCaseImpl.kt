package com.github.justalexandeer.socialnewsappclient.business.interactors.login

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.AuthorizationLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.TokenLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.UserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCaseImpl @Inject constructor(
    private val userRemoteRepository: UserRemoteRepository,
    private val tokenLocalRepository: TokenLocalRepository,
    private val authorizationLocalRepository: AuthorizationLocalRepository
): LoginUseCase {
    override operator fun invoke(username: String, password: String): Flow<DataState<Token?>> = flow {
        val dataState = remoteResultHandler(
            safeApiCall(Dispatchers.IO) {
                userRemoteRepository.loginUser(username, password)
            }
        )
        if(dataState.status == DataStateStatus.Success) {
            tokenLocalRepository.saveAccessToken(dataState.data!!.access_token)
            tokenLocalRepository.saveRefreshToken(dataState.data.refresh_token)
            authorizationLocalRepository.setAuthorizationFlag(false)
            authorizationLocalRepository.setFirstStartAppFlag(false)
            emit(dataState)
        } else {
            emit(dataState)
        }
    }
}