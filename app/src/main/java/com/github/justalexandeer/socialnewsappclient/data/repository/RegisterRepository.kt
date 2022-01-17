package com.github.justalexandeer.socialnewsappclient.data.repository

import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithoutToken
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepository @Inject constructor(
    private val apiWithoutToken: ApiWithoutToken
) {

    fun registerUser(username: String, name: String, password: String): Single<Response<Void>> {
        return apiWithoutToken.registerUser(username, name, password)
    }

}