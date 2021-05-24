package com.one4ll.xplayer.ui.image

import android.os.Bundle
import androidx.navigation.NavDirections
import com.one4ll.xplayer.R
import kotlin.Int
import kotlin.String

class GalleryFragmentDirections private constructor() {
  private data class ActionNavGalleryToFullImageFragment(
    val path: String
  ) : NavDirections {
    override fun getActionId(): Int = R.id.action_nav_gallery_to_fullImageFragment

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("path", this.path)
      return result
    }
  }

  companion object {
    fun actionNavGalleryToFullImageFragment(path: String): NavDirections =
        ActionNavGalleryToFullImageFragment(path)
  }
}
