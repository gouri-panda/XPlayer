package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.ImageRecylerViewAdapter
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val TAG = "galleryfragment"

class GalleryFragment : Fragment() {

    private lateinit var imageViewModel: ImageViewModel
    private lateinit var root: View
    private lateinit var exImageUri: ArrayList<Media>
    private lateinit var inImageUri: List<Media>

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        imageViewModel =
                ViewModelProviders.of(this).get(ImageViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(IO).launch {
            getImageUri()
        }
    }

    private suspend fun getImageUri() {
        withContext(IO) {
            exImageUri = getExternalContentImageUri(root.context)
            inImageUri = getInternalContentImageUri(root.context)
            exImageUri.addAll(inImageUri)
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
