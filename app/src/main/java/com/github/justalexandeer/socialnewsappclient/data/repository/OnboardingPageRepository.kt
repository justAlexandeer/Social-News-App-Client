package com.github.justalexandeer.socialnewsappclient.data.repository

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageButton
import com.github.justalexandeer.socialnewsappclient.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingPageRepository @Inject constructor() {

    fun getOnboardingPages(): List<OnboardingPage> {
        return createOnboardingPages()
    }

    private fun createOnboardingPages(): List<OnboardingPage> {
        return mutableListOf<OnboardingPage>().apply {
            add(
                OnboardingPage(
                    R.drawable.ic_youtuber_4519,
                    "Consectetur lorem donec massa sapien faucibus",
                    "Egestas erat imperdiet sed euismod nisi porta lorem mollis aliquam ut " +
                            "porttitor leo a diam sollicitudin tempor"
                )
            )
            add(
                OnboardingPage(
                    R.drawable.ic_social_media_post_4544,
                    "Sed libero enim sed faucibus turpis in eu",
                    "Enim ut tellus elementum sagittis vitae et leo duis ut diam quam" +
                            " nulla porttitor massa id neque"
                )
            )
            add(
                OnboardingPage(
                    R.drawable.ic_social_media_announcement_4548,
                    "egestas dui id ornare arcu",
                    "massa sed elementum tempus egestas sed sed risus pretium quam " +
                            "vulputate dignissim"
                )
            )
            add(
                OnboardingPage(
                    R.drawable.ic_youtuber_4533,
                    "dolor purus non enim praesent elementum facilisis leo vel",
                    "nunc mattis enim ut tellus elementum sagittis vitae et leo duis" +
                            " ut diam quam nulla porttitor massa id neque aliquam vestibulum morbi"
                )
            )
            add (
                OnboardingPage(
                    R.drawable.ic_social_media_marketing_4549,
                    "facilisis mauris sit amet massa vitae",
                    "fermentum et sollicitudin ac orci phasellus egestas tellus rutrum " +
                            "tellus pellentesque eu tincidunt tortor aliquam"
                )
                    )
        }
    }
}

data class OnboardingPage(
    val resId: Int,
    val titleText: String,
    val contextText: String
)