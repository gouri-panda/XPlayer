package com.one4ll.xplayer

import android.graphics.Bitmap
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

//fixme set watched var to 0.0f
data class Media(
    var name: String,
    var duration: String,
    var size: String,
    var path: String,
    var thumbnail: Bitmap? = null,
    var watched: Float = 0.2f
) {
    override fun toString(): String {
        return "name = $name, duration = $duration, size = $size, path = $path"
    }
}

class FakeMedia() : PreviewParameterProvider<Media> {
    val sherlock = Media(
        name = "Sherlock",
        duration = "1.23",
        size = "4.534",
        path = "Users/gouri/android/Sherlock",
        watched = 0.2f
    )

    //    val got = Media("Got", "4.3343", "4.534", "Users/gouri/android/Got")
//    val breakingBad = Media("Breaking Bad", "79.83", "43483", "Users/gouri/android/Breaking Bad")
//    val southPark = Media("South Park", "343.343", "4.534", "Users/gouri/android/South Park")
//    val office = Media("Office", "93.348", "34384", "Users/gouri/android/Office")
    override val values: Sequence<Media> = sequenceOf(sherlock)
}

