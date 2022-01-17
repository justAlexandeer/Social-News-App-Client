package com.github.justalexandeer.socialnewsappclient.data.repository

import com.github.justalexandeer.socialnewsappclient.data.sharedpreferences.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) {

    fun setAccessToken(accessToken: String) {
        sharedPreferencesManager.saveAccessToken(accessToken)
    }

    fun setRefreshToken(refreshToken: String) {
        sharedPreferencesManager.saveRefreshToken(refreshToken)
    }

    fun setAuthenticationFlag(isAuthenticated: Boolean) {
        sharedPreferencesManager.saveAuthenticationFlag(isAuthenticated)
    }

    fun getAccessToken(): String? {
        return sharedPreferencesManager.getAccessToken()
    }

    fun getRefreshToken(): String? {
        return sharedPreferencesManager.getRefreshToken()
    }

    fun getIsUserAuthenticated(): Boolean {
        return sharedPreferencesManager.getAuthenticationFlag()
    }

}