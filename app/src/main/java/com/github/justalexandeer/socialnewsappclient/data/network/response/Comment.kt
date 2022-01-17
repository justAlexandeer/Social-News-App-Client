package com.github.justalexandeer.socialnewsappclient.data.network.response

data class Comment(
    val id: Int,
    val content: String,
    val postId: Int,
    val appUser: AppUser,
    val answerList: List<Answer>
)