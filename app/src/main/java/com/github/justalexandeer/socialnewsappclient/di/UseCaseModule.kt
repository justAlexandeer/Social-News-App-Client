package com.github.justalexandeer.socialnewsappclient.di

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.CategoryLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.PostLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.CategoryRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.interactors.login.LoginUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.login.LoginUseCaseImpl
import com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.*
import com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding.CheckIsFirstStartAppUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding.CheckIsFirstStartAppUseCaseImpl
import com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding.GetOnboardingPagesUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.onboarding.GetOnboardingPagesUseCaseImpl
import com.github.justalexandeer.socialnewsappclient.business.interactors.registration.RegisterUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.registration.RegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindLoginUseCase(
        loginUseCaseImpl: LoginUseCaseImpl
    ): LoginUseCase

    @Binds
    abstract fun bindCheckIsFirstStartAppUseCase(
        checkIsFirstStartAppUseCaseImpl: CheckIsFirstStartAppUseCaseImpl
    ): CheckIsFirstStartAppUseCase

    @Binds
    abstract fun bindGetOnboardingPagesUseCase(
        getOnboardingPagesUseCaseImpl: GetOnboardingPagesUseCaseImpl
    ): GetOnboardingPagesUseCase

    @Binds
    abstract fun bindRegisterUseCase(
        registerUseCaseImpl: RegisterUseCaseImpl
    ): RegisterUseCase

    @Binds
    abstract fun GetDefaultCategoriesUseCase(
        getDefaultCategoriesUseCaseImpl: GetDefaultCategoriesUseCaseImpl
    ): GetDefaultCategoriesUseCase

    @Binds
    abstract fun GetLastSimplePostsByCategoryUseCase(
        getLastSimplePostsByCategoryUseCaseImpl: GetLastSimplePostsByCategoryUseCaseImpl
    ): GetLastSimplePostsByCategoryUseCase

    @Binds
    abstract fun GetTopSimplePostOfMonthUseCase(
        getTopSimplePostOfMonthUseCaseImpl: GetTopSimplePostOfMonthUseCaseImpl
    ): GetTopSimplePostOfMonthUseCase

    companion object {
        @Singleton
        @Provides
        fun provideGetDefaultCategoriesUseCase(
            categoryLocalRepository: CategoryLocalRepository,
            categoryRemoteRepository: CategoryRemoteRepository,
            @IoDispatcher ioDispatcher: CoroutineDispatcher
        ) = GetDefaultCategoriesUseCaseImpl(
            categoryLocalRepository,
            categoryRemoteRepository,
            ioDispatcher
        )

        @Singleton
        @Provides
        fun provideGetLastSimplePostByCategoryUseCase(
            getDefaultCategoriesUseCase: GetDefaultCategoriesUseCaseImpl,
            postLocalRepository: PostLocalRepository,
            postRemoteRepository: PostRemoteRepository,
            @IoDispatcher ioDispatcher: CoroutineDispatcher,
        ) = GetLastSimplePostsByCategoryUseCaseImpl(
            getDefaultCategoriesUseCase,
            postLocalRepository,
            postRemoteRepository,
            ioDispatcher
        )

        @Singleton
        @Provides
        fun provideGetTopSimplePostOfMountUseCase(
            postLocalRepository: PostLocalRepository,
            postRemoteRepository: PostRemoteRepository,
            @IoDispatcher ioDispatcher: CoroutineDispatcher,
        ) = GetTopSimplePostOfMonthUseCaseImpl(
            postLocalRepository,
            postRemoteRepository,
            ioDispatcher
        )
    }
}