package com.github.justalexandeer.socialnewsappclient.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceWithoutToken {
    private val okHttpClient = OkHttpClient().newBuilder()
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl("https://9da2-80-83-237-67.ngrok.io")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    companion object {
        val retrofit: ApiWithoutToken by lazy {ServiceWithoutToken().retrofit.create(ApiWithoutToken::class.java)}
    }
}