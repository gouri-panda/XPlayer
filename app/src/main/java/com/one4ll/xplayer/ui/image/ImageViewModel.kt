package com.one4ll.xplayer.ui.image

import android.app.Application
import androidx.lifecycle.*
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import kotlinx.coroutines.*

class ImageViewModel(val app: Application) : AndroidViewModel(app) {
    var imageUri: MutableLiveData<List<Media>> = MutableLiveData()

    init {
        viewModelScope.launch {
            imageUri.postValue(getImageUri())
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

}