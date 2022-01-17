package com.github.justalexandeer.socialnewsappclient.ui.newsline

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.justalexandeer.socialnewsappclient.data.network.response.Response
import com.github.justalexandeer.socialnewsappclient.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewsLineViewModel @Inject constructor(
    private val postRepository: PostRepository
): ViewModel () {

    init {
//        viewModelScope.launch {
//            response.value = testRepository.getTestResponse()
    }

    fun getPost() {
        postRepository
            .getPost("5")
            .mergeWith(postRepository.getPost("5"))
            .mergeWith(postRepository.getPost("5"))
            .mergeWith(postRepository.getPost("5"))
            .observeOn(Schedulers.io())
            .subscribe(
                {
                    Log.i(TAG, "getPost: ${it.status}")
                    if (it.data != null) {
                        Log.i(TAG, "getPost: ${it.data.content}")
                    }
                },
                {
                    Log.i(TAG, "getPost: ${it.localizedMessage}")
                }
            )
    }

    companion object {
        private const val TAG = "NewsLineViewModel"
    }
}