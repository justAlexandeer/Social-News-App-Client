package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.fake

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.PostLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import org.junit.Assert.*
import java.util.*
import kotlin.random.Random.Default.nextInt

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

    fun createListOfSimplePostWithCategory(category: Category): List<SimplePost> {
        val listOfSimplePost = mutableListOf<SimplePost>()
        repeat((0..10).random()) {
            listOfSimplePost.add(createSinglePage(category = category))
        }
        return listOfSimplePost
    }

    fun createSinglePage(category: Category): SimplePost {
        return SimplePost(
            randomLong(),
            UUID.randomUUID().toString(),
            randomLong(),
            AppUser(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            category,
            mutableListOf(),
            UUID.randomUUID().toString(),
            (0..1000).random()
        )
    }

    fun randomLong(): Long {
        return (0L..100000000L).random()
    }

}