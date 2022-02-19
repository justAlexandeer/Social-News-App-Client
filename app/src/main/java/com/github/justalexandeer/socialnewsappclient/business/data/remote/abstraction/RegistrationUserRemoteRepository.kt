package com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse

interface RegistrationUserRemoteRepository {

    suspend fun registerUser(username: String, name: String, password: String): RemoteResponse<Void>

}