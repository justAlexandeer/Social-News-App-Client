package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category

interface CategoryLocalRepository {
    suspend fun getListOfDefaultCategory(): List<Category>
    suspend fun saveListOfDefaultCategory(listCategories: List<Category>)
}