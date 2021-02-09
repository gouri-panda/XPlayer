package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.ImageRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_gallery.view.*


class GalleryFragment : Fragment() {

    private val imageViewModel: ImageViewModel by viewModels()
    private lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_gallery, container, false)
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
        val adapter = activity?.let { ImageRecyclerViewAdapter(imageList, activity = it) }
        root.image_list_recycler_view.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        }

    }

}
