package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.util.Log
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
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

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
        val time = measureTimeMillis {
            getImageUri()
        }
        Log.d(TAG, "onCreateView: time takes $time")
        return root
    }

    private  fun getImageUri() = CoroutineScope(IO).launch {
            exImageUri = getExternalContentImageUri(root.context)
            inImageUri = getInternalContentImageUri(root.context)
            exImageUri.addAll(inImageUri)
            setAdapterToUi()
    }
    private fun setAdapterToUi() = CoroutineScope(Main).launch {
            val adapter = ImageRecylerViewAdapter(exImageUri)
            val linearLayoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
            root.image_list_recycler_view.layoutManager = linearLayoutManager
            root.image_list_recycler_view.adapter = adapter
    }

}
