package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.fake

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.CategoryLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import org.junit.Assert.*

class FakeTestCategoryLocalRepository: CategoryLocalRepository {

    var data = mutableListOf<Category>()

    override suspend fun getListOfDefaultCategory(): List<Category> {
        return data.filter {
            it.isDefault
        }
    }

    override suspend fun saveListOfDefaultCategory(listCategories: List<Category>) {
        data.addAll(listCategories)
    }

    suspend fun getAllListOfDefaultCategory(): List<Category> {
        return data
    }

}