package com.github.justalexandeer.socialnewsappclient.business.data.remote

import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse

data class RemoteResult<T>(
    val status: RemoteResultStatus,
    val data: RemoteResponse<T>?,
    val errorMessage: String?
)

sealed class RemoteResultStatus {
    object Success : RemoteResultStatus()
    object Error : RemoteResultStatus()
}