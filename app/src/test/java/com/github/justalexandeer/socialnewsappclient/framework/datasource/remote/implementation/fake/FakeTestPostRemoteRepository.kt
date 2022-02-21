package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.fake

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.*
import java.util.*

class FakeTestPostRemoteRepository : PostRemoteRepository {

    var throwException = false

    override suspend fun getLastPostsByCategory(
        category: Category,
        pageNumber: Int,
        pageSize: Int
    ): RemoteResponse<Page<SimplePost>> {
        return if (throwException) {
            throw Exception()
        } else {
            createResponseWithSimplePostByCategory(category, pageNumber, pageSize)
        }
    }

    override suspend fun getTopSimplePostOfMonth(limit: Int): RemoteResponse<List<SimplePost>> {
        return if(throwException) {
            throw Exception()
        } else {
            return RemoteResponse("success", null, createListOfSimplePageByLimit(limit))
        }
    }

    fun createListOfSimplePageByLimit(limit: Int): List<SimplePost> {
        val listOfSimplePost = mutableListOf<SimplePost>()
        repeat(limit) {
            listOfSimplePost.add(createSingleSimplePost())
        }
        return listOfSimplePost
    }

    fun createResponseWithSimplePostByCategory(
        category: Category,
        pageNumber: Int,
        pageSize: Int
    ): RemoteResponse<Page<SimplePost>> {
        val remoteResponseData = mutableListOf<SimplePost>()
        repeat(pageSize) {
            remoteResponseData.add(createSingleSimplePost(category))
        }
        return RemoteResponse(
            "success",
            null,
            createPage(
                data = remoteResponseData,
                pageNumber = pageNumber,
                pageSize = pageSize
            )
        )
    }

    fun createSingleSimplePost(
        category: Category = createRandomCategory()
    ): SimplePost {
        return SimplePost(
            randomLong(),
            UUID.randomUUID().toString(),
            randomLong(),
            AppUser(UUID.randomUUID().toString(), UUID.randomUUID().toString(),(0..10).random()),
            category,
            mutableListOf(),
            UUID.randomUUID().toString(),
            (0..100).random()
        )
    }

    fun createPage(data: List<SimplePost>, pageNumber: Int, pageSize: Int): Page<SimplePost> {
        return Page(
            content = data,
            pageable = createPageable(pageNumber, pageSize),
            last = true,
            totalPages = 1,
            totalElements = pageSize,
            size = pageSize,
            number = pageNumber,
            sort = createSort(),
            first = true,
            numberOfElements = pageSize,
            empty = false
        )
    }

    fun createPageable(pageNumber: Int, pageSize: Int): Pageable {
        return Pageable(
            createSort(),
            0,
            pageNumber,
            pageSize,
            false,
            true
        )
    }

    fun createSort(): Sort {
        return Sort(
            empty = true,
            sorted = true,
            unsorted = false
        )
    }

    fun createRandomCategory(): Category {
        return Category(randomLong(), UUID.randomUUID().toString(), true)
    }

    fun randomLong(): Long {
        return (0L..100000000L).random()
    }
}