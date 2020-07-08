package com.one4ll.xplayer.ui.slideshow

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.RecylerViewAdapter
import com.one4ll.xplayer.helpers.getExternalContentMusicUri
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentMusicUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import com.one4ll.xplayer.models.Music
import kotlinx.android.synthetic.main.fragment_home.view.*

class SlideshowViewModel(application: Application) : AndroidViewModel(application) {
    var mapplication : Application
    init {
        this.mapplication = application
    }

    val musicList = MutableLiveData<List<Media>>()
    fun getMusicList(){
        val exUri = getExternalContentVideoUri(mapplication.applicationContext)
        val inUri = getInternalContentVideoUri(mapplication.applicationContext)
        exUri.addAll(inUri)
        exUri.forEach { println(it.name) }
        musicList.value = exUri
    }


}