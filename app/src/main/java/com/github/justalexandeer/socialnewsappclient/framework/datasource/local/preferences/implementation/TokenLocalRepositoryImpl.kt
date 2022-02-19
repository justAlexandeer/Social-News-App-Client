package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.TokenLocalRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenLocalRepositoryImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
): TokenLocalRepository {
    override fun saveAccessToken(accessToken: String) =
        sharedPreferencesManager.saveAccessToken(accessToken)

    override fun saveRefreshToken(refreshToken: String) =
        sharedPreferencesManager.saveRefreshToken(refreshToken)

    override fun getAccessToken(): String? =
        sharedPreferencesManager.getAccessToken()

    override fun getRefreshToken(): String? =
        sharedPreferencesManager.getRefreshToken()
}