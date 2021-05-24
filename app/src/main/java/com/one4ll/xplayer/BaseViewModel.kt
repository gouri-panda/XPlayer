package com.one4ll.xplayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getExternalContentMusicUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentMusicUri
import com.one4ll.xplayer.models.Streams
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class BaseViewModel(val app: Application) : AndroidViewModel(app) {
    private var _imageUri: MutableStateFlow<List<Media>> = MutableStateFlow(emptyList())
    var imageUri: StateFlow<List<Media>> = _imageUri
    var musicUriList: MutableLiveData<List<Media>> = MutableLiveData()
    private var _streamList: MutableStateFlow<List<Streams>> = MutableStateFlow(emptyList())
    var streamsList: StateFlow<List<Streams>> = _streamList
    var db: MediaDatabase = MediaDatabase.getInstance(app)


    init {
        viewModelScope.launch {
            _imageUri.value = getImageUri()
            getMusicListFromStorage()
            db.streamsDao().getAllByTime().collect {
                _streamList.value = it
            }

        }
    }

    private suspend fun getImageUri(): List<Media> {
        var exImageUri = emptyList<Media>()
        withContext(Dispatchers.IO) {
            val externalContentImageUriJob = async { getExternalContentImageUri(app.applicationContext) }
            val internalContentImageUriJob: Deferred<ArrayList<Media>> = async { getInternalContentImageUri(app.applicationContext) }
            exImageUri = externalContentImageUriJob.await() + internalContentImageUriJob.await()
        }
        return exImageUri
    }

    suspend fun getMusicListFromStorage() {
        withContext(Dispatchers.IO) {
            val externalMusicUri: Deferred<ArrayList<Media>> = async { getExternalContentMusicUri(app.baseContext) }
            val internalMusicUri: Deferred<ArrayList<Media>> = async { getInternalContentMusicUri(app.baseContext) }
            musicUriList.postValue(externalMusicUri.await() + internalMusicUri.await())
        }
    }
}