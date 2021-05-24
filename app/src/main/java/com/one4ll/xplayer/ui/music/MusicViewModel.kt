package com.one4ll.xplayer.ui.music

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.helpers.getExternalContentMusicUri
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentMusicUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MusicViewModel(private val app: Application) : AndroidViewModel(app) {

    init {
        viewModelScope.launch {
        }
    }




}