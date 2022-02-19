package com.github.justalexandeer.socialnewsappclient.business.domain.model

data class RemoteResponse<T> (
    val status: String,
    val message: String?,
    val data: T?
) {
    companion object {
        const val SUCCESS = "success"
        const val ERROR = "error"
    }
}
