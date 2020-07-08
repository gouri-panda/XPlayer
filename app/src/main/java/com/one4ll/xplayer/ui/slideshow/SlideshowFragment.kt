package com.one4ll.xplayer.ui.slideshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.R
import com.one4ll.xplayer.RecylerViewAdapter
import com.one4ll.xplayer.helpers.getExternalContentMusicUri
import com.one4ll.xplayer.helpers.getInternalContentMusicUri
import kotlinx.android.synthetic.main.fragment_slideshow.view.*

private val TAG = "audiofragment"
class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val exUri = getExternalContentMusicUri(root.context)
        val inUri = getInternalContentMusicUri(root.context)
        exUri.addAll(inUri)
        Log.d(TAG, "onCreateView: exsize ${exUri.size}")
        val adapter = RecylerViewAdapter(exUri)
        root.music_list_recycler_view.adapter = adapter
        val lineaLayoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL,false)
        root.music_list_recycler_view.layoutManager = lineaLayoutManager
        return root
    }
}