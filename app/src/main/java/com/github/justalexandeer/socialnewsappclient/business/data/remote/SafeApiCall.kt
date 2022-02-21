package com.github.justalexandeer.socialnewsappclient.business.data.remote

import android.util.Log
import com.github.justalexandeer.socialnewsappclient.business.domain.model.RemoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> RemoteResponse<T>,
): RemoteResult<T> {
    return withContext(dispatcher) {
        try {
            withTimeout(NETWORK_TIMEOUT) {
                RemoteResult(RemoteResultStatus.Success, apiCall.invoke(), null)
            }
        } catch (throwable: Throwable) {
            //Log.i(TAG, "${throwable.printStackTrace()}")
            when (throwable) {
                is TimeoutCancellationException -> {
                    RemoteResult(RemoteResultStatus.Error, null, NETWORK_ERROR_TIMEOUT)
                }
                is IOException -> {
                    RemoteResult(RemoteResultStatus.Error, null, NETWORK_ERROR_NO_INTERNET)
                }
                is HttpException -> {
                    RemoteResult(RemoteResultStatus.Error, null, throwable.localizedMessage)
                }
                else -> {
                    RemoteResult(RemoteResultStatus.Error, null, NETWORK_ERROR_UNKNOWN)
                }
            }
        }
    }
}

private const val TAG = "SafeApiCall"

const val NETWORK_TIMEOUT = 6000L

const val NETWORK_ERROR_UNKNOWN = "Network unknown problem"
const val NETWORK_ERROR_TIMEOUT = "Network timeout"
const val NETWORK_ERROR_NO_INTERNET = "No internet"