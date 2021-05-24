package com.one4ll.xplayer

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections

class FullImageFragmentDirections private constructor() {
  companion object {
    fun actionFullImageFragmentToCropImageFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_fullImageFragment_to_cropImageFragment)
  }
}
