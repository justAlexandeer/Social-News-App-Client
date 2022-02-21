package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.TagLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.safeCacheCall
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.TagRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTopTagsUseCaseImpl @Inject constructor(
    private val tagLocalRepository: TagLocalRepository,
    private val tagRemoteRepository: TagRemoteRepository,
    private val ioDispatcher: CoroutineDispatcher
): GetTopTagsUseCase {

    override suspend fun invoke(limit: Int): Flow<DataState<List<Tag>?>> = flow {
        val dataStateTagFromRemote = getTopOfTagsFromRemote(limit)
        if(dataStateTagFromRemote.status == DataStateStatus.Success) {
            saveTopTagsToLocal(dataStateTagFromRemote.data!!)
            emit(
                DataState<List<Tag>?>(
                    DataStateStatus.Success,
                    getTopTagsFromLocal(limit).data,
                    null,
                    null
                )
            )
        } else {
            emit(
                dataStateTagFromRemote.copy(
                    data = getTopTagsFromLocal(limit).data
                )
            )
        }
    }

    private suspend fun getTopOfTagsFromRemote(limit: Int): DataState<List<Tag>?> {
        return withContext(ioDispatcher) {
            remoteResultHandler(safeApiCall(ioDispatcher) {
                tagRemoteRepository.getTopTags(limit)
            })
        }
    }

    private suspend fun getTopTagsFromLocal(limit: Int): DataState<List<Tag>?> {
        return withContext(ioDispatcher) {
            safeCacheCall(ioDispatcher) {
                tagLocalRepository.getTopTags(limit)
            }
        }
    }

    private suspend fun saveTopTagsToLocal(listOfTag: List<Tag>) {
        safeCacheCall(ioDispatcher) {
            tagLocalRepository.saveTags(listOfTag)
        }
    }

}