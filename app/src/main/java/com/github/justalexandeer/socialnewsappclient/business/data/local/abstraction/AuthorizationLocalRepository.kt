package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

import kotlinx.coroutines.flow.SharedFlow

interface AuthorizationLocalRepository {

    fun setAuthorizationFlag(flag: Boolean)
    fun setFirstStartAppFlag(flag: Boolean)
    fun getAuthorizationFlag(): Boolean
    fun getFirstStartAppFlag(): Boolean
    fun getAuthorizationValueChange(): SharedFlow<Boolean>

}