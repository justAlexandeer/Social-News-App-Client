package com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.util

import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.AuthorizationLocalRepository
import com.github.justalexandeer.socialnewsappclient.business.data.local.abstraction.TokenLocalRepository
import com.github.justalexandeer.socialnewsappclient.framework.datasource.remote.ApiWithoutToken
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenLocalRepository: TokenLocalRepository,
    private val authorizationLocalRepository: AuthorizationLocalRepository,
    private val apiWithoutToken: ApiWithoutToken
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return try {
            synchronizedUpdateToken(response)
        } catch (ex: Exception) {
            null
        }
    }

    private fun synchronizedUpdateToken(response: Response): Request {
        synchronized(StateObject) {
            return if (response.request.header("Authorization")!! == tokenLocalRepository.getAccessToken()) {
                if (!authorizationLocalRepository.getAuthorizationFlag()) {
                    val refreshAccessTokenResponse =
                        apiWithoutToken.refreshAccessToken(tokenLocalRepository.getRefreshToken()!!).execute()

                    if (refreshAccessTokenResponse.isSuccessful) {
                        val accessToken = refreshAccessTokenResponse.body()!!.data!!.access_token
                        tokenLocalRepository.saveAccessToken(accessToken)
                        response.request.newBuilder()
                            .removeHeader("Authorization")
                            .addHeader("Authorization", accessToken)
                            .build()
                    } else {
                        authorizationLocalRepository.setAuthorizationFlag(true)
                        throw Exception("User need update refresh token")
                    }
                } else {
                    throw Exception("User not authenticated")
                }
            } else {
                val accessToken = tokenLocalRepository.getAccessToken()!!
                response.request.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", accessToken)
                    .build()
            }
        }
    }
}

private const val TAG = "TokenAuthenticator"

object StateObject