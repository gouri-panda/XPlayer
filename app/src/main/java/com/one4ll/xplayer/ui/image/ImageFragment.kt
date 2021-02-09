package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.ImageRecylerViewAdapter
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "galleryfragment"

class GalleryFragment : Fragment() {

    private val imageViewModel: ImageViewModel by viewModels()
    private lateinit var root: View
    private lateinit var exImageUri: List<Media>
    private lateinit var inImageUri: List<Media>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            getImageUri()
        }
    }

    private suspend fun getImageUri() {
        withContext(IO) {
            val externalContentImageUriJob = async { getExternalContentImageUri(root.context) }
            val internalContentImageUriJob: Deferred<ArrayList<Media>> = async { getInternalContentImageUri(root.context) }
            exImageUri = externalContentImageUriJob.await() + internalContentImageUriJob.await()
            setAdapterToUi()
        }
    }

    private suspend fun setAdapterToUi() {
        withContext(Main) {
            val adapter = activity?.let { ImageRecylerViewAdapter(exImageUri, activity = it) }
            root.image_list_recycler_view.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
            }

        }
    }

}
