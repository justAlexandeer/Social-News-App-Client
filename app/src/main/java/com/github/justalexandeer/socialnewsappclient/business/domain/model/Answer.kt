package com.github.justalexandeer.socialnewsappclient.business.domain.model

data class Answer(
    val id: Long,
    val content: String,
    val commentId: String,
    val appUser: AppUser
)