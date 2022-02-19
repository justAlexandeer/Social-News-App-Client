package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost

interface PostLocalRepository {
    suspend fun getListOfLastSimplePostByCategoryAndLimit(category: Category, limit: Int): List<SimplePost>
    suspend fun saveListOfSimplePost(listOfPost: List<SimplePost>)
    suspend fun getTopSimplePostOfMonth(limit: Int): List<SimplePost>
}