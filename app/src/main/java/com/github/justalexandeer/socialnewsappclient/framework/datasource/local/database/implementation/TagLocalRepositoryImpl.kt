package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.TagLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.TagDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers.TagMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TagLocalRepositoryImpl @Inject constructor(
    private val tagDao: TagDao,
    private val tagMapper: TagMapper
): TagLocalRepository {

    override suspend fun getTopTags(limit: Int): List<Tag> {
        return tagMapper.fromListOfEntityTagToListTag(tagDao.getTopTags(limit))
    }

    override suspend fun saveTags(listOfTag: List<Tag>) {
        tagDao.saveListOfTags(tagMapper.fromListOfTagToListEntityTag(listOfTag))
    }

}