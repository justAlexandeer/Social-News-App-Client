package com.github.justalexandeer.socialnewsappclient.business.interactors.registration

import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.UserRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCaseImpl @Inject constructor(
    private val userRemoteRepository: UserRemoteRepository
) : RegisterUseCase {
    override operator fun invoke(username: String, name: String, password: String): Flow<DataState<Void?>> = flow {
        val result = remoteResultHandler(safeApiCall(Dispatchers.IO) {
            userRemoteRepository.registerUser(username, name, password)
        })
        emit(result)
    }
}