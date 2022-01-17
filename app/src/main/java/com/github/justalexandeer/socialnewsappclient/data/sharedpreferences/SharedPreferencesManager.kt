package com.github.justalexandeer.socialnewsappclient.data.sharedpreferences

import android.content.SharedPreferences

class SharedPreferencesManager (
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) {

    fun saveAccessToken(accessToken: String) {
        with(sharedPreferencesEditor) {
            putString(ACCESS_TOKEN, accessToken)
            apply()
        }
    }

    fun saveRefreshToken(refreshToken: String) {
        with(sharedPreferencesEditor) {
            putString(REFRESH_TOKEN, refreshToken)
            apply()
        }
    }

    fun saveAuthenticationFlag(flag: Boolean) {
        with(sharedPreferencesEditor) {
            putBoolean(NEED_AUTHENTICATION, flag)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN, null)
    }

    fun getAuthenticationFlag(): Boolean {
        return sharedPreferences.getBoolean(NEED_AUTHENTICATION, false)
    }

    companion object {
        const val APP_PREFERENCES = "AppPreferences"
        private const val ACCESS_TOKEN = "AccessToken"
        private const val REFRESH_TOKEN = "RefreshToken"
        private const val NEED_AUTHENTICATION = "NeedAuthentication"
    }
}