package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.fake

import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.GetDefaultCategoriesUseCase
import org.junit.Assert.*

class FakeTestGetDefaultCategoriesUseCase: GetDefaultCategoriesUseCase {

    lateinit var data: DataState<List<Category>?>

    override suspend operator fun invoke(): DataState<List<Category>?> {
        return data
    }



}