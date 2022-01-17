package com.github.justalexandeer.socialnewsappclient.data.repository

import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithoutToken
import com.github.justalexandeer.socialnewsappclient.data.network.response.Token
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val apiWithoutToken: ApiWithoutToken
) {

    fun loginUser(userName: String, password: String): Single<Response<Token>> {
        return apiWithoutToken.loginUser(userName, password)
    }
}