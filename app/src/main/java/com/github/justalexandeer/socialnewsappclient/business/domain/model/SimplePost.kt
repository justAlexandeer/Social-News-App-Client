package com.github.justalexandeer.socialnewsappclient.business.domain.model

data class SimplePost(
    val id: Long,
    val name: String,
    val date: Long,
    val appUser: AppUser,
    val category: Category,
    val tags: List<Tag>,
    val content: String,
    val commentCount: Int
)
