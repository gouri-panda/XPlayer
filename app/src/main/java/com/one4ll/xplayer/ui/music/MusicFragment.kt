package com.one4ll.xplayer.ui.music

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
import com.one4ll.xplayer.adapter.MusicRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_slideshow.view.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "audioFragment"

class SlideshowFragment : Fragment() {

    private val musicViewModel: MusicViewModel by viewModels()
    private lateinit var musicRecyclerViewAdapter: MusicRecyclerViewAdapter
    private lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        musicRecyclerViewAdapter = MusicRecyclerViewAdapter(listOf(), lifecycleScope)
        musicViewModel.musicUriList.observe(viewLifecycleOwner, Observer { musicUriList ->
            if (musicUriList != null) {
                Log.d(TAG, "onCreateView: ex size ${musicUriList.size}")
                lifecycleScope.launch {
                    setAdapter(musicUriList, root)
                }
            }
        })
    }


    private suspend fun setAdapter(musicUriList: List<Media>, root: View) {
        musicRecyclerViewAdapter = MusicRecyclerViewAdapter(musicUriList, lifecycleScope)
        withContext(Main) {
            musicRecyclerViewAdapter.loadVideo(musicUriList)
            root.music_list_recycler_view.apply {
                this.adapter = musicRecyclerViewAdapter
                layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}