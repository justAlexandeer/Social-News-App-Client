package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.util

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.TokenLocalRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenInterceptor @Inject constructor(
    private val tokenLocalRepository: TokenLocalRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", tokenLocalRepository.getAccessToken()!!)
            .build()
        return chain.proceed(request)
    }
}