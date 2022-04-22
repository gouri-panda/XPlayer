package com.one4ll.xplayer.ui.image

import com.one4ll.xplayer.Media
import com.one4ll.xplayer.mvi.State

sealed class ImageFragmentState : State {
    object Loading : ImageFragmentState()
    data class ImageLoadedSuccess(val imagesList: List<Media>) : ImageFragmentState()
    data class Error(val message: String) : ImageFragmentState()

}