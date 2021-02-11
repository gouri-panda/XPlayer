package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.ImageRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_gallery.view.*

private const val TAG = "imageFragment"

class GalleryFragment : Fragment() {

    private val imageViewModel: ImageViewModel by viewModels()
    private lateinit var root: View

    private var imageRecyclerViewAdapter: ImageRecyclerViewAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_gallery, container, false)
        Log.d(TAG, "onCreateView: imageFragment view created")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageViewModel.imageUri.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                setAdapterToUi(it)
            }
        })
    }


    private fun setAdapterToUi(imageList: List<Media>) {
        imageRecyclerViewAdapter = activity?.let { ImageRecyclerViewAdapter(imageList, activity = it, lifeCycleScope = lifecycleScope) }
        root.image_list_recycler_view.apply {
            this.adapter = imageRecyclerViewAdapter
            layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        }

    }

}
