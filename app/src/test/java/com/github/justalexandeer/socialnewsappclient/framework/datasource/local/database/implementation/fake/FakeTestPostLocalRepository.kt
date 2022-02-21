package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.fake

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.PostLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import java.util.*

class FakeTestPostLocalRepository : PostLocalRepository {

    var data = mutableListOf<SimplePost>()

    override suspend fun getListOfLastSimplePostByCategoryAndLimit(
        category: Category,
        limit: Int
    ): List<SimplePost> {
        return data
            .filter { it.category == category }
            .take(limit)
            .sortedByDescending { it.date }
    }

    override suspend fun saveListOfSimplePost(listOfPost: List<SimplePost>) {
        data.addAll(listOfPost)
    }

    override suspend fun getTopSimplePostOfMonth(limit: Int): List<SimplePost> {
        return data
            .take(limit)
            .sortedByDescending { it.commentCount }
    }

    fun createListOfSimplePost(
        category: Category = createRandomCategory()
    ): List<SimplePost> {
        val listOfSimplePost = mutableListOf<SimplePost>()
        repeat((5..10).random()) {
            listOfSimplePost.add(createSinglePost(category = category))
        }
        return listOfSimplePost
    }

    fun createSinglePost(category: Category): SimplePost {
        return SimplePost(
            randomLong(),
            UUID.randomUUID().toString(),
            randomLong(),
            AppUser(UUID.randomUUID().toString(), UUID.randomUUID().toString(), (1..10).random()),
            category,
            mutableListOf(),
            UUID.randomUUID().toString(),
            (0..1000).random()
        )
    }

    fun randomLong(): Long {
        return (0L..100000000L).random()
    }

    fun createRandomCategory(): Category {
        return Category(randomLong(), UUID.randomUUID().toString(), true)
    }

}