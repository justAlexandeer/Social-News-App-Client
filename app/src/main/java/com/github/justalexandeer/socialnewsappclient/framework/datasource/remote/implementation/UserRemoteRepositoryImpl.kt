package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.UserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithToken
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithoutToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteRepositoryImpl @Inject constructor(
    private val apiWithoutToken: ApiWithoutToken,
    private val apiWithToken: ApiWithToken
): UserRemoteRepository {
    override suspend fun loginUser(username: String, password: String): RemoteResponse<Token> =
        apiWithoutToken.loginUser(username, password)

    override suspend fun registerUser(
        username: String,
        name: String,
        password: String
    ): RemoteResponse<Void> =
        apiWithoutToken.registerUser(username, name, password)

    override suspend fun getTopUser(limit: Int): RemoteResponse<List<AppUser>> =
        apiWithToken.getTopAuthors(limit)
}