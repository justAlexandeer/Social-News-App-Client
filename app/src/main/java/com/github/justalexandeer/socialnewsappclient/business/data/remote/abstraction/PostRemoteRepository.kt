package com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Page
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost

interface PostRemoteRepository {
    suspend fun getLastPostsByCategory(
        category: Category,
        pageNumber: Int,
        pageSize: Int
    ): RemoteResponse<Page<SimplePost>>
    suspend fun getTopSimplePostOfMonth(limit: Int): RemoteResponse<List<SimplePost>>
}