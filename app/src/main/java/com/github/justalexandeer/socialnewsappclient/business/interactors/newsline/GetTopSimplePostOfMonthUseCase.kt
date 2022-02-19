package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface GetTopSimplePostOfMonthUseCase {
    suspend operator fun invoke(limit: Int): Flow<DataState<List<SimplePost>?>>
}