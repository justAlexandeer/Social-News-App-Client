package com.github.justalexandeer.socialnewsappclient.business.domain.model

data class Comment(
    val id: Long,
    val content: String,
    val postId: Int,
    val appUser: AppUser,
    val answerList: List<Answer>
)