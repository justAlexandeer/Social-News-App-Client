package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface ApiWithoutToken {

    @FormUrlEncoded
    @POST("/login")
    suspend fun loginUser(
        @Field("username") userName: String,
        @Field("password") password: String
    ): RemoteResponse<Token>

    @POST("/api/registration")
    suspend fun registerUser(
        @Query("username") userName: String,
        @Query("name") name: String,
        @Query("password") password: String
    ): RemoteResponse<Void>

    @GET("api/token/refresh")
    fun refreshAccessToken(
        @Query("refreshToken") refreshToken: String
    ): Call<RemoteResponse<Token>>
}