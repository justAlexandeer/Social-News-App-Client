package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = tableName)
data class TagEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val amountOfUse: Int
)

private const val tableName = "tag_entity"