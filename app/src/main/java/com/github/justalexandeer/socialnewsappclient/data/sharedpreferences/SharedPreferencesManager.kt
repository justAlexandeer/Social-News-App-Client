package com.github.justalexandeer.socialnewsappclient.data.sharedpreferences

import android.content.SharedPreferences
import android.util.Log
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class SharedPreferencesManager(
    private val sharedPreferences: SharedPreferences,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) : SharedPreferences.OnSharedPreferenceChangeListener {

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    private val needAuthentication: BehaviorSubject<Boolean> =
        BehaviorSubject.createDefault(getAuthenticationFlag())

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

    fun getObservableAuthenticationFlag(): BehaviorSubject<Boolean> {
        return needAuthentication
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == NEED_AUTHENTICATION && sharedPreferences != null) {
            Log.i(TAG, "onSharedPreferenceChanged: ${sharedPreferences.getBoolean(NEED_AUTHENTICATION, false)}")
            needAuthentication.onNext(sharedPreferences.getBoolean(NEED_AUTHENTICATION, false))
        }
    }

    companion object {
        const val APP_PREFERENCES = "AppPreferences"
        private const val ACCESS_TOKEN = "AccessToken"
        private const val REFRESH_TOKEN = "RefreshToken"
        private const val NEED_AUTHENTICATION = "NeedAuthentication"
        private const val TAG = "SharedPreferencesManage"
    }
}