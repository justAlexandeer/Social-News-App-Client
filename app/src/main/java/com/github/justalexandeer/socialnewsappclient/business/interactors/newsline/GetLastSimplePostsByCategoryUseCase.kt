package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.model.SimplePost
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface GetLastSimplePostsByCategoryUseCase {
    suspend operator fun invoke(limit: Int): Flow<DataState<Map<Category, List<SimplePost>>>>
}