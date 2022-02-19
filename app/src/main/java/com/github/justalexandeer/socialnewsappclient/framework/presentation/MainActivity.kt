package com.github.justalexandeer.socialnewsappclient.framework.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.SocialNewsAppClient
import com.github.justalexandeer.socialnewsappclient.framework.presentation.ui.theme.SocialNewsAppClientTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationGraphicsApi
    @ExperimentalFoundationApi
    @InternalCoroutinesApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SocialNewsAppClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SocialNewsAppClient()
                }
            }
        }
    }

}