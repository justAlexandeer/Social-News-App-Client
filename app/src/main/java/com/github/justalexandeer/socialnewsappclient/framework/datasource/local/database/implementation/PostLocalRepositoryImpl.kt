package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.PostLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.SimplePostDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers.SimplePostMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLocalRepositoryImpl @Inject constructor(
    private val simplePostDao: SimplePostDao,
    private val simplePostMapper: SimplePostMapper
) : PostLocalRepository {

    override suspend fun getListOfLastSimplePostByCategoryAndLimit(
        category: Category,
        limit: Int
    ): List<SimplePost> {
        return simplePostMapper.fromListOfSimplePostEntityToListOfPost(
            simplePostDao.getListOfLastSimplePostByCategoryIdAndLimit(
                category.id,
                limit
            )
        )
    }

    override suspend fun saveListOfSimplePost(listOfSimplePost: List<SimplePost>) {
        simplePostDao.saveListOfSimplePost(
            simplePostMapper.fromListOfSimplePostToListOfSimplePostEntity(listOfSimplePost)
        )
    }

    override suspend fun getTopSimplePostOfMonth(limit: Int): List<SimplePost> {
        // one month ago
        val dateStart = System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30L
        return simplePostMapper.fromListOfSimplePostEntityToListOfPost(
            simplePostDao.getTopSimplePostOfMonth(
                dateStart,
                limit
            )
        )
    }
}