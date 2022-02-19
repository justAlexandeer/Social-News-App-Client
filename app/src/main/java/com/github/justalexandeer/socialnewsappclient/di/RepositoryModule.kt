package com.github.justalexandeer.socialnewsappclient.di

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.*
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.CategoryRemoteRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.implementation.OnboardingPagesLocalRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.LoginUserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.RegistrationUserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.CategoryLocalRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.PostLocalRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.implementation.AuthorizationLocalRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.implementation.TokenLocalRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.CategoryRemoteRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.LoginUserRemoteRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.PostRemoteRepositoryImpl
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.RegistrationUserRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindOnboardingPagesLocalRepository(
        onboardingPagesLocalRepositoryImpl: OnboardingPagesLocalRepositoryImpl
    ):  OnboardingPagesLocalRepository

    @Binds
    abstract fun bindRegistrationUserRemoteRepository(
        registrationUserRemoteRepositoryImpl: RegistrationUserRemoteRepositoryImpl
    ):  RegistrationUserRemoteRepository

    @Binds
    abstract fun bindLoginUserRemoteRepository(
        loginUserRemoteRepositoryImpl: LoginUserRemoteRepositoryImpl
    ): LoginUserRemoteRepository

    @Binds
    abstract fun bindTokenLocalRepository(
        tokenLocalRepositoryImpl: TokenLocalRepositoryImpl
    ): TokenLocalRepository

    @Binds
    abstract fun bindAuthorizationLocalRepository(
        authorizationLocalRepositoryImpl: AuthorizationLocalRepositoryImpl
    ): AuthorizationLocalRepository

    @Binds
    abstract fun bindCategoryLocalRepository(
        categoryLocalRepositoryImpl: CategoryLocalRepositoryImpl
    ): CategoryLocalRepository

    @Binds
    abstract fun bindCategoryRemoteRepository(
        categoryRemoteRepositoryImpl: CategoryRemoteRepositoryImpl
    ): CategoryRemoteRepository

    @Binds
    abstract fun bindPostRemoteRepository(
        postRemoteRepositoryImpl: PostRemoteRepositoryImpl
    ): PostRemoteRepository

    @Binds
    abstract fun bindPostLocalRepository(
        simplePostLocalRepositoryImpl: PostLocalRepositoryImpl
    ): PostLocalRepository

}
