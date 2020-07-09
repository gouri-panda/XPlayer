package com.one4ll.xplayer.ui.Music

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.R
import kotlinx.android.synthetic.main.fragment_slideshow.view.*
import kotlinx.android.synthetic.main.fragment_slideshow.view.button

private val TAG = "audiofragment"

class SlideshowFragment : Fragment() {

    private lateinit var musicViewModel: MusicViewModel
    private lateinit var adapter: MusicRecylerViewAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        musicViewModel =
                ViewModelProviders.of(this).get(MusicViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        adapter = MusicRecylerViewAdapter(listOf())
        root.button.setOnClickListener {
        musicViewModel.getMusicList()
        }
        musicViewModel.musicList.observe(viewLifecycleOwner, Observer { mediaList ->
            if (mediaList != null) {
                Log.d(TAG, "onCreateView: exsize ${mediaList.size}")
                adapter.loadVideo(mediaList)
                root.music_list_recycler_view.adapter = adapter
                val lineaLayoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
                root.music_list_recycler_view.layoutManager = lineaLayoutManager
            }
        })

        return root
    }
}