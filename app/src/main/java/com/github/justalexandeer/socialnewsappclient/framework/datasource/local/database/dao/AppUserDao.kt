package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.AppUserEntity
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.TagEntity

@Dao
interface AppUserDao {

    @Query("SELECT * FROM app_user_entity ORDER BY amountOfPosts DESC LIMIT :limit")
    fun getTopAppUserEntity(limit: Int): List<AppUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveListOfAppUserEntity(listOfAppUser: List<AppUserEntity>)
}