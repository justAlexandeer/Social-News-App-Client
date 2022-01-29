package com.github.justalexandeer.socialnewsappclient.data.network.util

import android.util.Log
import com.github.justalexandeer.socialnewsappclient.data.network.ApiWithoutToken
import com.github.justalexandeer.socialnewsappclient.data.repository.TokenRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

// Исправить постоянные !!
@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenRepository: TokenRepository,
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
            if (response.request.header("Authorization")!! == tokenRepository.getAccessToken()) {
                if (tokenRepository.getIsUserAuthenticated()) {
                    val refreshAccessTokenResponse =
                        apiWithoutToken.refreshAccessToken(tokenRepository.getRefreshToken()!!)
                            .execute()
                    if (refreshAccessTokenResponse.isSuccessful) {
                        val accessToken = refreshAccessTokenResponse.body()!!.data!!.access_token
                        tokenRepository.setAccessToken(accessToken)
                        return response.request.newBuilder()
                            .removeHeader("Authorization")
                            .addHeader("Authorization", accessToken)
                            .build()
                    } else {
                        tokenRepository.setAuthenticationFlag(false)
                        throw Exception("User need update refresh token")
                    }
                } else {
                    throw Exception("User not authenticated")
                }
            } else {
                val accessToken = tokenRepository.getAccessToken()!!
                return response.request.newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", accessToken)
                    .build()
            }
        }
    }

}

object StateObject