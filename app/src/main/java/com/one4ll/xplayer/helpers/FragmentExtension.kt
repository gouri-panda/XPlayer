package com.one4ll.xplayer.helpers

import androidx.fragment.app.Fragment
import com.one4ll.xplayer.BaseViewModel
import com.one4ll.xplayer.GalleryActivity

val Fragment.baseViewModel: BaseViewModel
    get() = (activity as GalleryActivity).baseViewModel