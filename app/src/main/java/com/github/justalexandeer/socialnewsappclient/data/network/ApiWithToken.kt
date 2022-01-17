package com.github.justalexandeer.socialnewsappclient.data.network

import com.github.justalexandeer.socialnewsappclient.data.network.response.FullPost
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import io.reactivex.rxjava3.core.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWithToken {

    @GET("/api/getPost")
    fun getPost(
        @Query("postId") postId: String
    ) : Single<Response<FullPost>>

}