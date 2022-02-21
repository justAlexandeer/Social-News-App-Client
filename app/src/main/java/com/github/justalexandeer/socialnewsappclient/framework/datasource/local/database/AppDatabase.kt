package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.convertor.Converters
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.AppUserDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.CategoryDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.SimplePostDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.TagDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.AppUserEntity
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.CategoryEntity
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.SimplePostEntity
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.entity.TagEntity

@Database(
    entities = [
        CategoryEntity::class,
        SimplePostEntity::class,
        TagEntity::class,
        AppUserEntity::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun simplePostDao(): SimplePostDao
    abstract fun tagDao(): TagDao
    abstract fun appUserDao(): AppUserDao
}

const val DATABASE_NAME = "App Database"
const val DATABASE_VERSION = 1