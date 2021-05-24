package com.one4ll.xplayer

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

data class FullImageFragmentArgs(
  val path: String
) : NavArgs {
  fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("path", this.path)
    return result
  }

  companion object {
    @JvmStatic
    fun fromBundle(bundle: Bundle): FullImageFragmentArgs {
      bundle.setClassLoader(FullImageFragmentArgs::class.java.classLoader)
      val __path : String?
      if (bundle.containsKey("path")) {
        __path = bundle.getString("path")
        if (__path == null) {
          throw IllegalArgumentException("Argument \"path\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"path\" is missing and does not have an android:defaultValue")
      }
      return FullImageFragmentArgs(__path)
    }
  }
}
