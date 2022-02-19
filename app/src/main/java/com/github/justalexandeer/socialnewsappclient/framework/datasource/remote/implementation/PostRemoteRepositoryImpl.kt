package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Page
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRemoteRepositoryImpl @Inject constructor(
    private val apiWithToken: ApiWithToken
) : PostRemoteRepository {

    override suspend fun getLastPostsByCategory(
        category: Category,
        pageNumber: Int,
        pageSize: Int
    ): RemoteResponse<Page<SimplePost>> =
        apiWithToken.getPageSimplePostByCategory(
            category.id,
            SORT_BY_DATE_DESCENDING,
            pageNumber,
            pageSize
        )

    override suspend fun getTopSimplePostOfMonth(limit: Int): RemoteResponse<List<SimplePost>> =
        apiWithToken.getTopSimplePostOfMonth(limit)


    companion object {
        const val SORT_BY_DATE_ASCENDING = "date_ascending"
        const val SORT_BY_DATE_DESCENDING = "date_descending"
    }


}