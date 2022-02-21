package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote

import com.github.justalexandeer.socialnewsappclient.business.domain.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWithToken {

//    @GET("/api/getPost")
//    suspend fun getPost(
//        @Query("postId") postId: String
//    ) : RemoteResponse<FullPost>

    @GET("/api/getDefaultCategories")
    suspend fun getDefaultCategory(): RemoteResponse<List<Category>>

    @GET("/api/getPosts")
    suspend fun getPageSimplePostByCategory(
        @Query("idCategory") idCategory: Long,
        @Query("sortBy") sortBy: String,
        @Query("page") pageNumber: Int,
        @Query("size") pageSize: Int
    ): RemoteResponse<Page<SimplePost>>

    @GET("/api/getTopPostsOfMonth")
    suspend fun getTopSimplePostOfMonth(
        @Query("limit") limit: Int
    ): RemoteResponse<List<SimplePost>>

    @GET("/api/getTopTags")
    suspend fun getTopTags(
        @Query("limit") limit: Int
    ): RemoteResponse<List<Tag>>

    @GET("/api/getTopAuthors")
    suspend fun getTopAuthors(
        @Query("limit") limit: Int
    ): RemoteResponse<List<AppUser>>

}