package com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.implementation

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.AuthorizationLocalRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.local.preferences.SharedPreferencesManager
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationLocalRepositoryImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
): AuthorizationLocalRepository {
    override fun setAuthorizationFlag(flag: Boolean) =
        sharedPreferencesManager.saveAuthorizationFlag(flag)

    override fun setFirstStartAppFlag(flag: Boolean) =
        sharedPreferencesManager.saveFirstStartAppFlag(flag)

    override fun getAuthorizationFlag(): Boolean =
        sharedPreferencesManager.getAuthorizationFlag()

    override fun getFirstStartAppFlag(): Boolean =
        sharedPreferencesManager.getFirstStartApp()

    override fun getAuthorizationValueChange(): SharedFlow<Boolean> =
        sharedPreferencesManager.getObservableAuthorizationFlag()
}