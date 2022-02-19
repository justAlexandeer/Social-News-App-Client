package com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction

interface TokenLocalRepository {
    fun saveAccessToken(accessToken: String)
    fun saveRefreshToken(refreshToken: String)
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
}