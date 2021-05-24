package com.one4ll.xplayer.ui.image

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class ImageViewModel(val app: Application) : AndroidViewModel(app) {
    private var _imageUri: MutableStateFlow<List<Media>> = MutableStateFlow(emptyList())
    var imageUri: StateFlow<List<Media>> = _imageUri

    init {
        viewModelScope.launch {
            _imageUri.value = getImageUri()
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