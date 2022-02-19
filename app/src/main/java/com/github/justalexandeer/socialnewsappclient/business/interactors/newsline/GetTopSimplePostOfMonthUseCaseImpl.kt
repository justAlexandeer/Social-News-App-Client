package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import android.util.Log
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.PostLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.safeCacheCall
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.PostRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateErrorType
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTopSimplePostOfMonthUseCaseImpl @Inject constructor(
    private val postLocalRepository: PostLocalRepository,
    private val postRemoteRepository: PostRemoteRepository,
    private val dispatcherIO: CoroutineDispatcher
) : GetTopSimplePostOfMonthUseCase {

    override suspend fun invoke(limit: Int): Flow<DataState<List<SimplePost>?>> = flow {
        val dataSimplePostFromRemote = getTopOfSimplePostFromRemote(limit)
        if(dataSimplePostFromRemote.status == DataStateStatus.Success) {
            saveTopOfSimplePostToLocal(dataSimplePostFromRemote.data!!)
            Log.i("TAG", "invoke: ${dataSimplePostFromRemote.data}")
            Log.i("TAG", "invoke: ${getTopOfSimplePostFromLocal(limit).data}")
            emit(
                DataState<List<SimplePost>?> (
                    DataStateStatus.Success,
                    getTopOfSimplePostFromLocal(limit).data,
                    null,
                    null
                )
            )
        } else {
            emit(
                dataSimplePostFromRemote.copy(
                    data = getTopOfSimplePostFromLocal(limit).data
                )
            )
        }
    }

    private suspend fun getTopOfSimplePostFromRemote(limit: Int): DataState<List<SimplePost>?> {
        return withContext(dispatcherIO) {
            remoteResultHandler(safeApiCall(dispatcherIO) {
                postRemoteRepository.getTopSimplePostOfMonth(limit)
            })
        }
    }

    private suspend fun getTopOfSimplePostFromLocal(limit: Int): DataState<List<SimplePost>?> {
        return withContext(dispatcherIO) {
            safeCacheCall(dispatcherIO) {
                postLocalRepository.getTopSimplePostOfMonth(limit)
            }
        }
    }

    private suspend fun saveTopOfSimplePostToLocal(listOfSimplePost: List<SimplePost>) {
        safeCacheCall(dispatcherIO) {
            postLocalRepository.saveListOfSimplePost(listOfSimplePost)
        }
    }

}