package com.github.justalexandeer.socialnewsappclient.data.network.util

import com.github.justalexandeer.socialnewsappclient.data.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenInterceptor @Inject constructor(
    val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", tokenRepository.getAccessToken()!!)
            .build()
        return chain.proceed(request)
    }
}