package com.one4ll.xplayer.ui.image

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.one4ll.xplayer.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ImageFragmentStateViewModel(app: Application) : BaseViewModel(app) {
    private val _state: MutableStateFlow<ImageFragmentState> =
        MutableStateFlow(ImageFragmentState.Loading)
    val state: StateFlow<ImageFragmentState> = _state

    init {
        viewModelScope.launch {
            //todo add image to the flow if [MediaStoreApi] addes one
            imageUri.collect {
                _state.value = ImageFragmentState.ImageLoadedSuccess(imagesList = it)
            }
        }
    }
}