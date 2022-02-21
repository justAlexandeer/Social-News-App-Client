package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateErrorType
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.fake.FakeTestGetDefaultCategoriesUseCase
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.database.implementation.fake.FakeTestPostLocalRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.implementation.fake.FakeTestPostRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Test
import java.lang.Exception

class GetLastSimplePostsByCategoryUseCaseTest {

    val fakeTestPostLocalRepository = FakeTestPostLocalRepository()
    var fakeTestPostRemoteRepository = FakeTestPostRemoteRepository()
    var fakeTestGetDefaultCategoriesUseCase = FakeTestGetDefaultCategoriesUseCase()
    val getLastSimplePostsByCategoryUseCase = GetLastSimplePostsByCategoryUseCaseImpl(
        fakeTestGetDefaultCategoriesUseCase,
        fakeTestPostLocalRepository,
        fakeTestPostRemoteRepository,
        Dispatchers.Unconfined
    )


    @After
    fun resetFakeTestRepositoryAndFakeUseCaseData() {
        fakeTestPostLocalRepository.data = mutableListOf()
        fakeTestPostRemoteRepository = FakeTestPostRemoteRepository()
        fakeTestGetDefaultCategoriesUseCase = FakeTestGetDefaultCategoriesUseCase()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun useCase_CategoryDataStateErrorRemote_returnErrorRemote() {
        runBlocking {
            fakeTestGetDefaultCategoriesUseCase.data = DataState(
                DataStateStatus.Error,
                null,
                "",
                DataStateErrorType.Remote
            )
            val limit = 5
            var dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).first()
            assertThat(dataState.status, IsEqual(DataStateStatus.Error))
            assertThat(dataState.data, IsEqual(null))
            assertThat(dataState.errorType, IsEqual(DataStateErrorType.Remote))
            assertThat(fakeTestPostLocalRepository.data, IsEqual(mutableListOf()))
            try {
                dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun useCase_errorRemoteAndInLocalNoData_returnRemoteErrorWithNoLocalData() {
        runBlocking {
            fakeTestGetDefaultCategoriesUseCase.data = DataState(
                DataStateStatus.Success,
                mutableListOf(
                    Category(1L, "test name", true),
                    Category(2L, "test name2", true)
                ),
                null,
                null
            )
            val limit = 5
            fakeTestPostRemoteRepository.throwException = true

            var dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).first()
            assertThat(dataState.status, IsEqual(DataStateStatus.Error))
            assert(dataState.data!!.isEmpty())
            assertThat(dataState.errorType, IsEqual(DataStateErrorType.Remote))
            assertThat(fakeTestPostLocalRepository.data, IsEqual(mutableListOf()))
            try {
                dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }
    }


    @Test
    fun useCase_errorRemoteAndInLocalHasData_returnErrorWithLocalData() {
        runBlocking {
            fakeTestGetDefaultCategoriesUseCase.data = DataState(
                DataStateStatus.Success,
                mutableListOf(
                    Category(1L, "test name", true),
                    Category(2L, "test name2", true)
                ),
                null,
                null
            )
            val limit = 5
            fakeTestPostRemoteRepository.throwException = true
            val listOfCategories = fakeTestGetDefaultCategoriesUseCase.data.data
            val mapCategoryAndListPostFromLocal = mutableMapOf<Category, List<SimplePost>>()
            listOfCategories!!.forEach {
                fakeTestPostLocalRepository.saveListOfSimplePost(
                    fakeTestPostLocalRepository.createListOfSimplePost(
                        it
                    )
                )
                val listWithCurrentCategory =
                    fakeTestPostLocalRepository.getListOfLastSimplePostByCategoryAndLimit(it, limit)
                if (listWithCurrentCategory.isNotEmpty()) {
                    mapCategoryAndListPostFromLocal[it] = listWithCurrentCategory
                }

            }

            var dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).first()
            assertThat(dataState.status, IsEqual(DataStateStatus.Error))
            assertThat(dataState.data, IsEqual(mapCategoryAndListPostFromLocal))
            assertThat(dataState.errorType, IsEqual(DataStateErrorType.Remote))
            try {
                dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }
    }

    @Test
    fun useCase_SuccessRemoteAndInLocalNoData_returnSuccessWithLocalData() {
        runBlocking {
            fakeTestGetDefaultCategoriesUseCase.data = DataState(
                DataStateStatus.Success,
                mutableListOf(
                    Category(1L, "test name", true),
                    Category(2L, "test name2", true)
                ),
                null,
                null
            )
            val limit = 5
            val listOfCategories = fakeTestGetDefaultCategoriesUseCase.data.data

            var dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).first()

            val mapCategoryAndListPostFromLocal = mutableMapOf<Category, List<SimplePost>>()
            listOfCategories!!.forEach {
                mapCategoryAndListPostFromLocal[it] =
                    fakeTestPostLocalRepository.getListOfLastSimplePostByCategoryAndLimit(it, limit)
            }

            assertThat(dataState.status, IsEqual(DataStateStatus.Success))
            assertThat(dataState.data, IsEqual(mapCategoryAndListPostFromLocal))
            assertThat(dataState.errorMessage, IsNull())
            assertThat(dataState.errorType, IsNull())
            try {
                dataState = getLastSimplePostsByCategoryUseCase.invoke(limit).drop(1).first()
            } catch (e: Exception) {
                assertThat(e.message, IsEqual("Expected at least one element"))
            }
        }


    }
}