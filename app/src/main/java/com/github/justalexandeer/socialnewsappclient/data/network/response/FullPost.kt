package com.github.justalexandeer.socialnewsappclient.data.network.response

data class FullPost(
    val id: Int,
    val name: String,
    val date: Long,
    val appUser: AppUser,
    val category: Category,
    val tags: List<Tag>,
    val content: String,
    val pageableComments: Page<Comment>
)