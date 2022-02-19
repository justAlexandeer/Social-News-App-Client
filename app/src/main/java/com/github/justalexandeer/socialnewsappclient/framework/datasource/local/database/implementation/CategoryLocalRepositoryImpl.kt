package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.CategoryLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.CategoryDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers.CategoryMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryLocalRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val mapper: CategoryMapper
): CategoryLocalRepository {

    override suspend fun getListOfDefaultCategory(): List<Category> {
        return mapper.fromListOfCategoryEntityToListOfCategories(categoryDao.getAllDefault())
    }

    override suspend fun saveListOfDefaultCategory(listCategories: List<Category>) {
        return categoryDao.saveAll(mapper.fromListCategoryToListOfCategoryEntity(listCategories))
    }
}