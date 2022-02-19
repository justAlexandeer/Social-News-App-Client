package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.mappers

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.CategoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryMapper @Inject constructor(){

    fun fromListCategoryToListOfCategoryEntity(listCategories: List<Category>): List<CategoryEntity> {
        return listCategories.map {
            return@map CategoryEntity(it.id, it.name, it.isDefault)
        }
    }

    fun fromListOfCategoryEntityToListOfCategories(listCategories: List<CategoryEntity>): List<Category> {
        return listCategories.map {
            return@map Category(it.id, it.name, it.isDefault)
        }
    }

}