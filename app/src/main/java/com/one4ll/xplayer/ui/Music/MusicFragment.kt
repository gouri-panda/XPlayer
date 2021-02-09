package com.one4ll.xplayer.ui.Music

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.MusicRecylerViewAdapter
import kotlinx.android.synthetic.main.fragment_slideshow.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val TAG = "audiofragment"

class SlideshowFragment : Fragment() {

    private lateinit var musicViewModel: MusicViewModel
    private lateinit var adapter: MusicRecylerViewAdapter
    private lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        musicViewModel =
                ViewModelProviders.of(this).get(MusicViewModel::class.java)
        adapter = MusicRecylerViewAdapter(listOf())
        musicViewModel.musicList.observe(viewLifecycleOwner, Observer { mediaList ->
            if (mediaList != null) {
                Log.d(TAG, "onCreateView: exsize ${mediaList.size}")
                CoroutineScope(Main).launch {
                    setAdapter(mediaList, root)
                }
            }
        })
    }

    private suspend fun setAdapter(mediaList: List<Media>, root: View) {
        withContext(Main) {
            adapter.loadVideo(mediaList)
            root.music_list_recycler_view.apply {
                this.adapter = adapter
                layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}