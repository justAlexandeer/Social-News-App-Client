package com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag

interface TagRemoteRepository {

    suspend fun getTopTags(limit: Int): RemoteResponse<List<Tag>>

}