package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

import com.github.justalexandeer.socialnewsappclient.business.domain.model.AppUser
import com.github.justalexandeer.socialnewsappclient.business.domain.model.Tag

interface UserLocalRepository {
    suspend fun getTopUsers(limit: Int): List<AppUser>
    suspend fun saveUsers(listOfTag: List<AppUser>)
}