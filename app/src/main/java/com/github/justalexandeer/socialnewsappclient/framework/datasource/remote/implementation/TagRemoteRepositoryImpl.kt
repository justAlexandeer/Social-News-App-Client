package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.TagRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagRemoteRepositoryImpl @Inject constructor(
    private val apiWithToken: ApiWithToken
): TagRemoteRepository {

    override suspend fun getTopTags(limit: Int): RemoteResponse<List<Tag>> =
        apiWithToken.getTopTags(limit)

}