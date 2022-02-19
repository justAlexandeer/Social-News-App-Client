package com.github.justalexandeer.socialnewsappclient.business.interactors.registration

import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    operator fun invoke(username: String, name: String, password: String): Flow<DataState<Void?>>
}