package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateErrorType
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.fake.FakeTestPostLocalRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.fake.FakeTestPostRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Test
import java.lang.Exception

class GetTopSimplePostOfMonthUseCaseTest {

    val fakeTestPostLocalRepository = FakeTestPostLocalRepository()
    var fakeTestPostRemoteRepository = FakeTestPostRemoteRepository()
    val getTopSimplePostOfMonthUseCase = GetTopSimplePostOfMonthUseCaseImpl(
        fakeTestPostLocalRepository,
        fakeTestPostRemoteRepository,
        Dispatchers.Unconfined
    )

    @After
    fun resetFakeTestRepository() {
        fakeTestPostLocalRepository.data = mutableListOf()
        fakeTestPostRemoteRepository = FakeTestPostRemoteRepository()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun useCase_errorRemoteAndInLocalNoData_returnRemoteErrorWithNoLocalData() {
        runBlocking {
            val limit = 5
            fakeTestPostRemoteRepository.throwException = true

            var dataState = getTopSimplePostOfMonthUseCase.invoke(limit).first()
            assertThat(dataState.status, IsEqual(DataStateStatus.Error))
            assertThat(dataState.data, IsEqual(fakeTestPostLocalRepository.getTopSimplePostOfMonth(limit)))
            assertThat(dataState.errorType, IsEqual(DataStateErrorType.Remote))
            assertThat(fakeTestPostLocalRepository.data, IsEqual(mutableListOf()))
            try {
                dataState = getTopSimplePostOfMonthUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }
    }

    @Test
    fun useCase_errorRemoteAndInLocalHasData_returnErrorWithLocalData() {
        runBlocking {
            val limit = 5
            fakeTestPostRemoteRepository.throwException = true
            fakeTestPostLocalRepository.data = fakeTestPostLocalRepository.createListOfSimplePost().toMutableList()

            var dataState = getTopSimplePostOfMonthUseCase.invoke(limit).first()
            assertThat(dataState.status, IsEqual(DataStateStatus.Error))
            assertThat(dataState.errorType, IsEqual(DataStateErrorType.Remote))
            assertThat(dataState.data, IsEqual(fakeTestPostLocalRepository.getTopSimplePostOfMonth(limit)))
            try {
                dataState = getTopSimplePostOfMonthUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }
    }

    @Test
    fun useCase_successRemoteAndInLocalNoData_returnSuccessWithLocalData() {
        runBlocking {
            val limit = 5

            var dataState = getTopSimplePostOfMonthUseCase.invoke(limit).first()
            assertThat(dataState.status, IsEqual(DataStateStatus.Success))
            assertThat(dataState.errorMessage, IsNull())
            assertThat(dataState.errorType, IsNull())
            assertThat(dataState.data, IsEqual(fakeTestPostLocalRepository.getTopSimplePostOfMonth(limit)))
            try {
                dataState = getTopSimplePostOfMonthUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }
    }

}