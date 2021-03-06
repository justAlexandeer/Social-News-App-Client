package com.github.justalexandeer.socialnewsappclient.di

import android.content.Context
import androidx.room.Room
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.AppDatabase
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.DATABASE_NAME
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.AppUserDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.CategoryDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.SimplePostDao
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.dao.TagDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent:: class)
@Module
object DatabaseModule {

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideSimplePostDao(database: AppDatabase): SimplePostDao {
        return database.simplePostDao()
    }

    @Provides
    fun provideTagDao(database: AppDatabase): TagDao {
        return database.tagDao()
    }

    @Provides
    fun provideAppUser(database: AppDatabase): AppUserDao {
        return database.appUserDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}