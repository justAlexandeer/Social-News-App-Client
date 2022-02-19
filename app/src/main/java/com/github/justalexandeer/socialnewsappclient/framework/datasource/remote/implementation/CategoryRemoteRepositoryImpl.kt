package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.CategoryRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRemoteRepositoryImpl @Inject constructor(
    private val apiWithToken: ApiWithToken
): CategoryRemoteRepository {

    override suspend fun getDefaultCategories(): RemoteResponse<List<Category>> {
        return apiWithToken.getDefaultCategory()
    }

}