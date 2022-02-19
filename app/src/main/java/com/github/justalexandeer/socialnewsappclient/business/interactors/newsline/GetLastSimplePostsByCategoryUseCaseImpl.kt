package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.PostLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.safeCacheCall
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Page
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLastSimplePostsByCategoryUseCaseImpl @Inject constructor(
    private val getDefaultCategoryUseCase: GetDefaultCategoriesUseCase,
    private val postLocalRepository: PostLocalRepository,
    private val postRemoteRepository: PostRemoteRepository,
    private val dispatcherIO: CoroutineDispatcher
) : GetLastSimplePostsByCategoryUseCase {

    override suspend operator fun invoke(limit: Int) = flow<DataState<Map<Category, List<SimplePost>>>> {
        val dataStateDefaultCategory = getDefaultCategoryUseCase.invoke()
        if (dataStateDefaultCategory.status == DataStateStatus.Success) {
            val listDataStateRemotePost =
                getListOfDataStateRemotePost(limit, dataStateDefaultCategory.data!!)
            if (areAllResponseSuccessful(listDataStateRemotePost)) {
                val mapRemotePostByCategory = matchListOfPostWithCategory(
                    dataStateDefaultCategory.data,
                    listDataStateRemotePost.map { it.data!!.content }
                )
                saveListOfRemotePostByCategoryToLocal(mapRemotePostByCategory)
                emit(
                    DataState(
                        DataStateStatus.Success,
                        getMapLocalPostByCategory(
                            limit,
                            dataStateDefaultCategory.data
                        ),
                        null,
                        null
                    )
                )
            } else {
                emit(
                    findResponseWithError(listDataStateRemotePost).copy(
                        data = getMapLocalPostByCategory(
                            limit,
                            dataStateDefaultCategory.data
                        )
                    )
                )
            }
        } else {
            emit(
                DataState(
                    dataStateDefaultCategory.status,
                    null,
                    dataStateDefaultCategory.errorMessage,
                    dataStateDefaultCategory.errorType
                )
            )
        }
    }

    private suspend fun getMapLocalPostByCategory(
        limit: Int,
        listOfDefaultCategory: List<Category>
    ): Map<Category, List<SimplePost>> {
        return withContext(dispatcherIO) {
            val listDeferred = mutableListOf<Deferred<DataState<List<SimplePost>?>>>()
            listOfDefaultCategory.forEach {
                listDeferred.add(
                    async {
                        safeCacheCall(dispatcherIO) {
                            postLocalRepository.getListOfLastSimplePostByCategoryAndLimit(it, limit)
                        }
                    }
                )
            }
            val listDataStateLocalPost = mutableListOf<List<SimplePost>>()
            listDeferred.forEach {
                listDataStateLocalPost.add(it.await().data!!)
            }
            return@withContext matchListOfPostWithCategory(
                listOfDefaultCategory,
                listDataStateLocalPost.toList()
            )
        }
    }

    private suspend fun saveListOfRemotePostByCategoryToLocal(
        map: Map<Category, List<SimplePost>>
    ) {
        withContext(dispatcherIO) {
            val listDeferred = mutableListOf<Deferred<DataState<Any?>>>()
            map.entries.forEach {
                listDeferred.add(
                    async {
                        safeCacheCall(dispatcherIO) {
                            postLocalRepository.saveListOfSimplePost(
                                it.value
                            )
                        }
                    }
                )
            }
            listDeferred.forEach {
                it.await()
            }
        }
    }

    private fun matchListOfPostWithCategory(
        listOfDefaultCategory: List<Category>,
        listOfListOfPost: List<List<SimplePost>>
    ): Map<Category, List<SimplePost>> {
        val mapCategoryWithSimplePost = mutableMapOf<Category, List<SimplePost>>()
        listOfDefaultCategory.forEach { category ->
            val listWithCurrentCategory = listOfListOfPost.find {
                it.any { simplePost ->
                    simplePost.category == category
                }
            }
            if(listWithCurrentCategory != null) {
                mapCategoryWithSimplePost[category] = listWithCurrentCategory
            }
        }
        return mapCategoryWithSimplePost
    }

    private fun findResponseWithError(
        listDataStateRemotePost: List<DataState<Page<SimplePost>?>>
    ): DataState<Map<Category, List<SimplePost>>> {
        val errorDataStateRemoteResponse = listDataStateRemotePost.find {
            it.status == DataStateStatus.Error
        }!!
        return DataState(
            errorDataStateRemoteResponse.status,
            null,
            errorDataStateRemoteResponse.errorMessage,
            errorDataStateRemoteResponse.errorType
        )
    }

    private fun areAllResponseSuccessful(
        listDataStateRemotePost: List<DataState<Page<SimplePost>?>>
    ): Boolean {
        return listDataStateRemotePost.all {
            it.status == DataStateStatus.Success
        }
    }

    private suspend fun getListOfDataStateRemotePost(
        limit: Int,
        ListOfDefaultCategory: List<Category>
    ): List<DataState<Page<SimplePost>?>> {
        return withContext(dispatcherIO) {
            val listDeferred = mutableListOf<Deferred<DataState<Page<SimplePost>?>>>()
            ListOfDefaultCategory.forEach {
                listDeferred.add(
                    async {
                        remoteResultHandler(safeApiCall(dispatcherIO) {
                            postRemoteRepository.getLastPostsByCategory(it, 0, limit)
                        })
                    }
                )
            }
            val listDataStateRemotePost = mutableListOf<DataState<Page<SimplePost>?>>()
            listDeferred.forEach {
                listDataStateRemotePost.add(it.await())
            }
            return@withContext listDataStateRemotePost
        }
    }
}