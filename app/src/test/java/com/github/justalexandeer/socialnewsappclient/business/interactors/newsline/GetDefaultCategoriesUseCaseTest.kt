package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateErrorType
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.fake.FakeTestCategoryLocalRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.fake.FakeTestCategoryRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Test

class GetDefaultCategoriesUseCaseTest {

    val fakeTestCategoryLocalRepository = FakeTestCategoryLocalRepository()
    var fakeTestCategoryRemoteRepository = FakeTestCategoryRemoteRepository()
    val getDefaultCategoriesUseCase = GetDefaultCategoriesUseCaseImpl(
        fakeTestCategoryLocalRepository,
        fakeTestCategoryRemoteRepository,
        Dispatchers.Unconfined
    )

    @After
    fun resetFakeTestRepositoryData() {
        fakeTestCategoryLocalRepository.data = mutableListOf()
        fakeTestCategoryRemoteRepository = FakeTestCategoryRemoteRepository()
    }


    @Test
    fun useCase_NoDataInLocalAndInRemoteError_returnError() {
        fakeTestCategoryRemoteRepository.throwException = true

        runBlocking {
            val dataState = getDefaultCategoriesUseCase.invoke()
            assertThat(dataState.status, IsEqual(DataStateStatus.Error))
            assertThat(dataState.data, IsEqual(null))
            assertThat(dataState.errorType, IsEqual(DataStateErrorType.Remote))
            assertThat(fakeTestCategoryLocalRepository.data, IsEqual(mutableListOf()))
        }
    }

    @Test
    fun useCase_NoDataInLocalAndRemoteSuccess_returnSaveToLocalAndReturnFromLocal() {
        val dataFromRemote = mutableListOf(
            Category(1L, "Test1", true),
            Category(2L, "Test2", true),
            Category(3L, "Test3", true),
            Category(4L, "Test4", true),
        )
        fakeTestCategoryRemoteRepository.response = RemoteResponse(
            "success", null, dataFromRemote
        )

        runBlocking {
            val dataState = getDefaultCategoriesUseCase.invoke()
            assertThat(dataState.status, IsEqual(DataStateStatus.Success))
            assertThat(dataState.data, IsEqual(dataFromRemote))
            assertThat(dataState.errorMessage, IsEqual(null))
            assertThat(dataState.errorType, IsEqual(null))
            assertThat(fakeTestCategoryLocalRepository.data, IsEqual(dataFromRemote))
        }
    }

    @Test
    fun useCase_HasDataInLocal_returnFromLocal() {
        val dataFromLocal = mutableListOf(
            Category(1L, "Test1", true),
            Category(2L, "Test2", true),
            Category(3L, "Test3", true),
            Category(4L, "Test4", true),
        )
        fakeTestCategoryLocalRepository.data = dataFromLocal

        runBlocking {
            val dataState = getDefaultCategoriesUseCase.invoke()
            assertThat(dataState.status, IsEqual(DataStateStatus.Success))
            assertThat(dataState.data, IsEqual(dataFromLocal))
            assertThat(dataState.errorMessage, IsEqual(null))
            assertThat(dataState.errorType, IsEqual(null))
            assertThat(fakeTestCategoryLocalRepository.data, IsEqual(dataFromLocal))
        }
    }

}