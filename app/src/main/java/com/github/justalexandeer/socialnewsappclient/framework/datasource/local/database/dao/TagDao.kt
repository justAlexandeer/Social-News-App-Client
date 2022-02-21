package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.TagEntity

@Dao
interface TagDao {

    @Query("SELECT * FROM tag_entity ORDER BY amountOfUse DESC LIMIT :limit")
    fun getTopTags(limit: Int): List<TagEntity>

    @Insert(onConflict = REPLACE)
    fun saveListOfTags(listOfTag: List<TagEntity>)
}