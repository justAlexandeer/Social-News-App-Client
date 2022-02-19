package com.github.justalexandeer.socialnewsappclient.business.interactors.login

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Token
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(username: String, password: String): Flow<DataState<Token?>>
}