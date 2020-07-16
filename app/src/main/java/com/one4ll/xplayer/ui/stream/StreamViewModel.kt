package com.one4ll.xplayer.ui.stream

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.models.Streams

class StreamViewModel(application: Application) : AndroidViewModel(application) {
    var streamsList: LiveData<List<Streams>>
    var db: MediaDatabase

    init {
        db = MediaDatabase.getInstance(application)
        streamsList = db.streamsDao().getAllByTime()
    }
}