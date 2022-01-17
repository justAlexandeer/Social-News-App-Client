package com.github.justalexandeer.socialnewsappclient.data.network.response

data class Answer(
    val id: String,
    val content: String,
    val commentId: String,
    val appUser: AppUser
)