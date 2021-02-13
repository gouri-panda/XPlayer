package com.one4ll.xplayer.ui.stream

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.models.Streams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class StreamViewModel(application: Application) : AndroidViewModel(application) {
    private var _streamList: MutableStateFlow<List<Streams>> = MutableStateFlow(emptyList())
    var streamsList: StateFlow<List<Streams>> = _streamList
    var db: MediaDatabase = MediaDatabase.getInstance(application)

    init {
        viewModelScope.launch {
            db.streamsDao().getAllByTime().collect {
                _streamList.value = it
            }

        }

    }
}