package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import android.util.Log
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.UserLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.safeCacheCall
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.UserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
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
class GetTopUsersUseCaseImpl @Inject constructor(
    private val userLocalRepository: UserLocalRepository,
    private val userRemoteRepository: UserRemoteRepository,
    private val ioDispatcher: CoroutineDispatcher
): GetTopUsersUseCase {

    override suspend fun invoke(limit: Int): Flow<DataState<List<AppUser>?>> = flow {
        val dataStateAppUsersFromRemote = getTopOfAppUsersFromRemote(limit)
        if(dataStateAppUsersFromRemote.status == DataStateStatus.Success) {
            saveTopAppUsersToLocal(dataStateAppUsersFromRemote.data!!)
            Log.i("TAG", "invoke: ${dataStateAppUsersFromRemote.data}")
            Log.i("TAG", "invoke: ${getTopAppUsersFromLocal(5)}")
            emit(
                DataState<List<AppUser>?>(
                    DataStateStatus.Success,
                    getTopAppUsersFromLocal(limit).data,
                    null,
                    null
                )
            )
        } else {
            emit(
                dataStateAppUsersFromRemote.copy(
                    data = getTopAppUsersFromLocal(limit).data
                )
            )
        }
    }

    private suspend fun getTopOfAppUsersFromRemote(limit: Int): DataState<List<AppUser>?> {
        return withContext(ioDispatcher) {
            remoteResultHandler(safeApiCall(ioDispatcher) {
                userRemoteRepository.getTopUser(limit)
            })
        }
    }

    private suspend fun getTopAppUsersFromLocal(limit: Int): DataState<List<AppUser>?> {
        return withContext(ioDispatcher) {
            safeCacheCall(ioDispatcher) {
                userLocalRepository.getTopUsers(limit)
            }
        }
    }

    private suspend fun saveTopAppUsersToLocal(listOfAppUser: List<AppUser>) {
        safeCacheCall(ioDispatcher) {
            userLocalRepository.saveUsers(listOfAppUser)
        }
    }

}