package com.github.justalexandeer.socialnewsappclient.di

import android.content.Context
import android.content.SharedPreferences
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.SharedPreferencesManager
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.SharedPreferencesManager.Companion.APP_PREFERENCES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(
        sharedPreferences: SharedPreferences,
        sharedPreferencesEditor: SharedPreferences.Editor
    ): SharedPreferencesManager {
        return SharedPreferencesManager(sharedPreferences, sharedPreferencesEditor)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context): SharedPreferences {
        return applicationContext.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

}