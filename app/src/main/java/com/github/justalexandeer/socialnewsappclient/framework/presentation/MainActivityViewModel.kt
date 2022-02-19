package com.github.justalexandeer.socialnewsappclient.framework.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.AuthorizationLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authorizationLocalRepository: AuthorizationLocalRepository
) : ViewModel() {

    val needAuthorization = mutableStateOf(authorizationLocalRepository.getAuthorizationFlag())

    init {
        viewModelScope.launch {
            authorizationLocalRepository.getAuthorizationValueChange().collect {
                needAuthorization.value = it
            }
        }
    }

    companion object {
        private const val TAG = "MainActivityViewModel"
    }
}