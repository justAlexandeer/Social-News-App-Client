package com.github.justalexandeer.socialnewsappclient.business.data.remote

import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataState
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateErrorType
import com.github.justalexandeer.socialnewsappclient.business.domain.state.DataStateStatus


private const val success = "success"
private const val error = "error"

fun <T> remoteResultHandler (remoteResult: RemoteResult<T>): DataState<T?> {
    return when (remoteResult.status) {
        RemoteResultStatus.Success -> {
            if (remoteResult.data!!.status == success) {
                DataState(DataStateStatus.Success, remoteResult.data.data, null, null)
            } else {
                DataState(DataStateStatus.Error, null, remoteResult.data.message, DataStateErrorType.Business)
            }
        }
        RemoteResultStatus.Error -> {
            DataState(DataStateStatus.Error, null, remoteResult.errorMessage, DataStateErrorType.Remote)
        }
    }
}

