package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category_entity where isDefault = 1")
    suspend fun getAllDefault(): List<CategoryEntity>

    @Insert(onConflict = REPLACE)
    suspend fun saveAll(list: List<CategoryEntity>)

}