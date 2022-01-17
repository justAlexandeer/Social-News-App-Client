package com.github.justalexandeer.socialnewsappclient.data.network

import com.github.justalexandeer.socialnewsappclient.data.network.response.Token
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiWithoutToken {

    @FormUrlEncoded
    @POST("/login")
    fun loginUser(
        @Field("username") userName: String,
        @Field("password") password: String
    ): Single<Response<Token>>

    @POST("/api/registration")
    fun registerUser(
        @Query("username") userName: String,
        @Query("name") name: String,
        @Query("password") password: String
    ): Single<Response<Void>>

    @GET("api/token/refresh")
    fun refreshAccessToken(
        @Query("refreshToken") refreshToken: String
    ): Call<Response<Token>>
}