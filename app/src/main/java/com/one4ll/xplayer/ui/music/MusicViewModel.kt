package com.one4ll.xplayer.ui.music

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

class MusicViewModel(private val app: Application) : AndroidViewModel(app) {

    val musicList = MutableLiveData<List<Media>>()
    suspend fun getMusicList() {
        withContext(IO) {
            val exUri = getExternalContentVideoUri(app.applicationContext)
            val inUri = getInternalContentVideoUri(app.applicationContext)
            exUri.addAll(inUri)
            exUri.forEach { println(it.name) }
            withContext(Main) {
                musicList.value = exUri
            }
        }
    }


}