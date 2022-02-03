package com.github.justalexandeer.socialnewsappclient.data.repository

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
                    R.raw.animated_vector_write_news,
                    "Consectetur lorem donec massa sapien",
                    "Egestas erat imperdiet sed euismod nisi porta lorem mollis aliquam ut " +
                            "porttitor leo a diam sollicitudin tempor"
                )
            )
            add(
                OnboardingPage(
                    R.raw.animated_vector_share_news,
                    "Sed libero enim sed faucibus turpis in eu",
                    "Enim ut tellus elementum sagittis vitae et leo duis ut diam quam" +
                            " nulla porttitor massa id neque"
                )
            )
            add(
                OnboardingPage(
                    R.raw.animated_vector_create_account,
                    "egestas dui id ornare arcu  enim sed in",
                    "massa sed elementum tempus egestas sed sed risus pretium quam " +
                            "vulputate dignissim"
                )
            )
            add(
                OnboardingPage(
                    R.raw.animated_vector_discuss,
                    "dolor purus non enim praesent elementum ",
                    "nunc mattis enim ut tellus elementum sagittis vitae et leo duis" +
                            " ut diam quam nulla porttitor massa id neque aliquam vestibulum morbi"
                )
            )
            add (
                OnboardingPage(
                    R.raw.animated_vector_add_to_favorite,
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