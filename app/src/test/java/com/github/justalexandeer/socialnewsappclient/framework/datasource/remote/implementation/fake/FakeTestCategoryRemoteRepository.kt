package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.fake

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.CategoryRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse

class FakeTestCategoryRemoteRepository: CategoryRemoteRepository {

    lateinit var response: RemoteResponse<List<Category>>
    var throwException = false

    override suspend fun getDefaultCategories(): RemoteResponse<List<Category>> {
        return if (throwException) {
            throw Exception()
        } else {
            response
        }
    }

}