package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote

import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.util.AccessTokenInterceptor
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.util.LoggingInterceptor
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.util.TokenAuthenticator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceWithToken @Inject constructor(
    accessTokenInterceptor: AccessTokenInterceptor,
    tokenAuthenticator: TokenAuthenticator
) {
    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(accessTokenInterceptor)
        .addInterceptor(LoggingInterceptor())
        .authenticator(tokenAuthenticator)
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://abdd-95-161-223-143.ngrok.io")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}