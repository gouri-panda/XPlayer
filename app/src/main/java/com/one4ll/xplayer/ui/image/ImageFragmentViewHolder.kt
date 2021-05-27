package com.one4ll.xplayer.ui.image

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.one4ll.xplayer.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ImageFragmentViewHolder(app: Application) : BaseViewModel(app) {
    val state: MutableStateFlow<ImageFragmentState> = MutableStateFlow(ImageFragmentState.Loading)

    init {
        viewModelScope.launch {
            //todo add image to the flow if [MediaStoreApi] addes one
            imageUri.collect {
                state.value = ImageFragmentState.ImageLoadedSuccess(imagesList = it)
            }
        }
    }
}