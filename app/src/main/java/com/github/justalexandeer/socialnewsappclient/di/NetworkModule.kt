package com.github.justalexandeer.socialnewsappclient.di

import com.github.justalexandeer.socialnewsappclient.data.network.ServiceWithToken
import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithToken
import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithoutToken
import com.github.justalexandeer.socialnewsappclient.data.network.ServiceWithoutToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiWithToken(serviceWithToken: ServiceWithToken): ApiWithToken {
        return serviceWithToken.retrofit.create(ApiWithToken::class.java)
    }

    @Singleton
    @Provides
    fun provideApiWithoutToken(): ApiWithoutToken {
        return ServiceWithoutToken.retrofit
    }

}