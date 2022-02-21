package com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token

interface UserRemoteRepository {

    suspend fun loginUser(username: String, password: String): RemoteResponse<Token>
    suspend fun registerUser(username: String, name: String, password: String): RemoteResponse<Void>
    suspend fun getTopUser(limit: Int): RemoteResponse<List<AppUser>>

}