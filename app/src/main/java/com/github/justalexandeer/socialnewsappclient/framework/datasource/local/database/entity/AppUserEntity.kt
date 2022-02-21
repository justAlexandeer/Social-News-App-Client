package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = tableName)
data class AppUserEntity (
    val name: String,
    @PrimaryKey
    val username: String,
    val amountOfPosts: Int
)

private const val tableName = "app_user_entity"