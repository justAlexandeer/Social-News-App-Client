package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.fake

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.*
import java.util.*

class FakeTestPostRemoteRepository : PostRemoteRepository {

    lateinit var response: RemoteResponse<Page<SimplePost>>
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

    fun createResponseWithSimplePostByCategory(
        category: Category,
        pageNumber: Int,
        pageSize: Int
    ): RemoteResponse<Page<SimplePost>> {
        val remoteResponseData = mutableListOf<SimplePost>()
        repeat(pageSize) {
            remoteResponseData.add(createSinglePage(category))
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

    fun createSinglePage(category: Category): SimplePost {
        return SimplePost(
            randomLong(),
            UUID.randomUUID().toString(),
            randomLong(),
            AppUser(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
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

    fun randomLong(): Long {
        return (0L..100000000L).random()
    }
}