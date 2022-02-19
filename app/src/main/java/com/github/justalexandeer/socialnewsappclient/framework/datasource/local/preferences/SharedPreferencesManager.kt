package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences

import android.content.SharedPreferences
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) : SharedPreferences.OnSharedPreferenceChangeListener {

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    private val needAuthorization = MutableSharedFlow<Boolean>()

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

    fun saveAuthorizationFlag(flag: Boolean) {
        with(sharedPreferencesEditor) {
            putBoolean(NEED_AUTHORIZATION, flag)
            apply()
        }
    }

    fun saveFirstStartAppFlag(flag: Boolean) {
        with(sharedPreferencesEditor) {
            putBoolean(FIRST_START_APP, flag)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN, null)
    }

    fun getAuthorizationFlag(): Boolean {
        return sharedPreferences.getBoolean(NEED_AUTHORIZATION, true)
    }

    fun getFirstStartApp(): Boolean {
        return sharedPreferences.getBoolean(FIRST_START_APP, true)
    }

    fun getObservableAuthorizationFlag(): MutableSharedFlow<Boolean> {
        return needAuthorization
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == FIRST_START_APP && sharedPreferences != null) {
            Log.i(TAG, "onSharedPreferenceChanged: ${getFirstStartApp()}")
        }
        if (key == NEED_AUTHORIZATION && sharedPreferences != null) {
            CoroutineScope(Dispatchers.Default).launch {
                needAuthorization.emit(getAuthorizationFlag())
            }
        }
    }

    companion object {
        const val APP_PREFERENCES = "AppPreferences"
        private const val ACCESS_TOKEN = "AccessToken"
        private const val REFRESH_TOKEN = "RefreshToken"
        private const val NEED_AUTHORIZATION = "NeedAuthorization"
        private const val FIRST_START_APP = "FirstStartApp"
        private const val TAG = "SharedPreferencesManage"
    }
}