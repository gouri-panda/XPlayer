package com.one4ll.xplayer.helpers

import androidx.fragment.app.Fragment
import com.one4ll.xplayer.BaseViewModel
import com.one4ll.xplayer.GalleryActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
val Fragment.baseViewModel: BaseViewModel
    get() = (activity as GalleryActivity).baseViewModel