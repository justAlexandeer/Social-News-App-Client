package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.RegistrationUserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithoutToken
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistrationUserRemoteRepositoryImpl @Inject constructor(
    private val apiWithoutToken: ApiWithoutToken
): RegistrationUserRemoteRepository {

    override suspend fun registerUser(
        username: String,
        name: String,
        password: String
    ): RemoteResponse<Void> =
        apiWithoutToken.registerUser(username, name, password)


}