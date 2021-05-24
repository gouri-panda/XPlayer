package com.one4ll.xplayer.helpers

import androidx.fragment.app.Fragment
import com.one4ll.xplayer.BaseViewModel
import com.one4ll.xplayer.GalleryActivity
import com.one4ll.xplayer.MainActivity

val Fragment.baseViewModel: BaseViewModel
    get() = (activity as GalleryActivity).mainViewModel