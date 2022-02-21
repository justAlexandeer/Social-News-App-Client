package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag

interface TagLocalRepository {
    suspend fun getTopTags(limit: Int): List<Tag>
    suspend fun saveTags(listOfTag: List<Tag>)
}