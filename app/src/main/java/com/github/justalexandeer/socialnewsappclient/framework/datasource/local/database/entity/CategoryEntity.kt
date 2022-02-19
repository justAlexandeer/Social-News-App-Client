package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = tableName)
data class CategoryEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val isDefault: Boolean
)

private const val tableName = "category_entity"
