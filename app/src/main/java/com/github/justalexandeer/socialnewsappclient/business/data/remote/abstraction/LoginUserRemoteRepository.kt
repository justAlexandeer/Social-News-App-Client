package com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token

interface LoginUserRemoteRepository {

    suspend fun loginUser(username: String, password: String): RemoteResponse<Token>

}