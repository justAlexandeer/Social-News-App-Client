package com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse

interface CategoryRemoteRepository {

    suspend fun getDefaultCategories(): RemoteResponse<List<Category>>

}