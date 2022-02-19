package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.SimplePostEntity

@Dao
interface SimplePostDao {

    @Query("SELECT * FROM simple_post_entity")
    suspend fun getAllSimplePost(): List<SimplePostEntity>

    @Query("SELECT * FROM simple_post_entity WHERE categoryid = :categoryId ORDER BY date DESC LIMIT :limit")
    suspend fun getListOfLastSimplePostByCategoryIdAndLimit(
        categoryId: Long,
        limit: Int
    ): List<SimplePostEntity>

    @Query("SELECT * FROM simple_post_entity WHERE date > :dateStart ORDER BY commentCount DESC, DATE ASC LIMIT :limit")
    suspend fun getTopSimplePostOfMonth(
        dateStart: Long,
        limit: Int
    ): List<SimplePostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSimplePost(post: SimplePostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListOfSimplePost(listOfPost: List<SimplePostEntity>)

}