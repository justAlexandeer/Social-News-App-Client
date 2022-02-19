package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity

import androidx.room.*
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag

@Entity(tableName = tableName)
data class SimplePostEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val date: Long,
    @Embedded(prefix = "app_user")
    val appUser: AppUser,
    @Embedded(prefix = "category")
    val category: Category,
    val tags: ArrayList<Tag>,
    val content: String,
    val commentCount: Int
)

private const val tableName = "simple_post_entity"