package com.github.justalexandeer.socialnewsappclient.data.network.response

data class Response<T> (
    val status: String,
    val message: String?,
    val data: T?
)
