package com.one4ll.xplayer.ui.music

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.MusicRecyclerViewAdapter
import com.one4ll.xplayer.databinding.FragmentMusicBinding
import com.one4ll.xplayer.helpers.baseViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "audioFragment"

class SlideshowFragment : Fragment() {

    private lateinit var musicRecyclerViewAdapter: MusicRecyclerViewAdapter
    private lateinit var binding: FragmentMusicBinding
    private lateinit var musicListRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_music, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        musicListRecyclerView = view.findViewById(R.id.music_list_recycler_view)
        musicRecyclerViewAdapter = MusicRecyclerViewAdapter(listOf(), lifecycleScope)
        baseViewModel.musicUriList.observe(viewLifecycleOwner, Observer { musicUriList ->
            if (musicUriList != null) {
                Log.d(TAG, "onCreateView: ex size ${musicUriList.size}")
                lifecycleScope.launch {
                    setAdapter(musicUriList, binding.root)
                }
            }
        })
    }


    private suspend fun setAdapter(musicUriList: List<Media>, root: View) {
        withContext(Main) {
            musicRecyclerViewAdapter.loadVideo(musicUriList)
            musicListRecyclerView.apply {
                this.adapter = musicRecyclerViewAdapter
                layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
}