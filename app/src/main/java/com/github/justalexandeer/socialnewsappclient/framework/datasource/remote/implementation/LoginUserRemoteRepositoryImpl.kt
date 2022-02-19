package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.LoginUserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithoutToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUserRemoteRepositoryImpl @Inject constructor(
    private val apiWithoutToken: ApiWithoutToken
): LoginUserRemoteRepository {
    override suspend fun loginUser(username: String, password: String): RemoteResponse<Token> =
        apiWithoutToken.loginUser(username, password)
}