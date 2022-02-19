package com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.socialnews.newsline

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.GetDefaultCategoriesUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.GetLastSimplePostsByCategoryUseCase
import com.github.justalexandeer.socialnewsappclient.business.interactors.newsline.GetTopSimplePostOfMonthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsLineViewModel @Inject constructor(
    private val getLastSimplePostsByCategory: GetLastSimplePostsByCategoryUseCase,
    private val getDefaultCategoriesUseCase: GetDefaultCategoriesUseCase,
    private val getTopSimplePostOfMonthUseCase: GetTopSimplePostOfMonthUseCase
) : ViewModel() {

    init {

    }


    fun getPost() {
        viewModelScope.launch {

            val data = getTopSimplePostOfMonthUseCase.invoke(5).collect {
                Log.i(TAG, "getPost: ${it.data}")
            }
            Log.i(TAG, "getPost: $data")
//            getLastSimplePostsByCategory.invoke().collect {
//
//                if(it.status == DataStateStatus.Success) {
//                    Log.i(TAG, "getPost: Status Success")
//                    it.data!!.values.forEach { list ->
//                        Log.i(TAG, "getPost +: $list")
//                    }
//                }
//                if(it.status == DataStateStatus.Error) {
//                    Log.i(TAG, "getPost: Status Error")
//                    Log.i(TAG, "getPost: ${it.errorMessage}")
//                    it.data?.values?.forEach { list ->
//                        Log.i(TAG, "getPost -: $list")
//                    }
//                }
//                Log.i(TAG, "getPost: i dont know what is ")
//            }


//            if(dataState.status == DataStateStatus.Success) {
//                dataState.data!!.forEach {
//                    Log.i(TAG, "getPost: ${it.key.name}")
//                    it.value.forEach {
//                        Log.i(TAG, "getPost:     ${it.name}")
//                    }
//                }
//            } else {
//                Log.i(TAG, "getPost: error")
//                Log.i(TAG, "${dataState.errorMessage}")
//            }

        }

    }


    companion object {
        private const val TAG = "NewsLineViewModel"
    }
}