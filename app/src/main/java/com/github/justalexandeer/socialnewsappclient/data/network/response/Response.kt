package com.github.justalexandeer.socialnewsappclient.data.network.response

data class Response<T> (
    val status: String,
    val message: String?,
    val data: T?
) {
    companion object {
        const val SUCCESS = "success"
        const val ERROR = "error"
    }
}
