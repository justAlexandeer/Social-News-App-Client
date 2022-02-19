package com.github.justalexandeer.socialnewsappclient.business.interactors.newsline

import androidx.annotation.VisibleForTesting
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.CategoryLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.safeCacheCall
import com.github.justalexandeer.socialnewsappclient.business.data.remote.abstraction.CategoryRemoteRepository
import com.github.justalexandeer.socialnewsappclient.business.data.remote.remoteResultHandler
import com.github.justalexandeer.socialnewsappclient.business.data.remote.safeApiCall
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Category
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDefaultCategoriesUseCaseImpl @Inject constructor(
    private val categoryLocalRepository: CategoryLocalRepository,
    private val categoryRemoteRepository: CategoryRemoteRepository,
    private val dispatcherIO: CoroutineDispatcher
) : GetDefaultCategoriesUseCase {

    override suspend operator fun invoke(): DataState<List<Category>?> = withContext(dispatcherIO) {
        val dataCategoryFromLocal = getDefaultCategoryFromLocal()
        if (dataCategoryFromLocal.data!!.isEmpty() || dataCategoryFromLocal.status == DataStateStatus.Error) {
            val dataCategoryFromRemote = getDefaultCategoryFromRemote()
            if (dataCategoryFromRemote.status == DataStateStatus.Error)
                return@withContext dataCategoryFromRemote
            saveDefaultCategoryToLocal(dataCategoryFromRemote)
            return@withContext getDefaultCategoryFromLocal()
        } else {
            return@withContext dataCategoryFromLocal
        }
    }

    private suspend fun getDefaultCategoryFromLocal(): DataState<List<Category>?> {
        return safeCacheCall(dispatcherIO) {
            categoryLocalRepository.getListOfDefaultCategory()
        }
    }

    private suspend fun getDefaultCategoryFromRemote(): DataState<List<Category>?> {
        return remoteResultHandler(safeApiCall(dispatcherIO) {
            categoryRemoteRepository.getDefaultCategories()
        })
    }

    private suspend fun saveDefaultCategoryToLocal(dataCategoryFromRemote: DataState<List<Category>?>) {
        safeCacheCall(dispatcherIO) {
            categoryLocalRepository.saveListOfDefaultCategory(dataCategoryFromRemote.data!!)
        }
    }

}