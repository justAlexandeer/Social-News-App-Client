package com.github.justalexandeer.socialnewsappclient.data.repository

import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithToken
import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithoutToken
import com.github.justalexandeer.socialnewsappclient.data.network.response.FullPost
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val apiWithToken: ApiWithToken
) {

    fun getPost(postId: String): Single<Response<FullPost>> {
        return apiWithToken.getPost(postId)
    }

}