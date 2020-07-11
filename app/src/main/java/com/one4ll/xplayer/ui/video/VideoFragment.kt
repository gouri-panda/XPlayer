package com.one4ll.xplayer.ui.video

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.VideoRecylerViewAdapter
import com.one4ll.xplayer.helpers.IS_GRID_LAYOUT
import com.one4ll.xplayer.helpers.SHARED_PREF_SETTINGS
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.math.log

private val TAG = "homefragment"

class VideoFragment : Fragment() {
    private lateinit var videoViewModel: VideoViewModel
    private lateinit var root: View
    private lateinit var job: Job


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        videoViewModel =
                ViewModelProviders.of(this).get(VideoViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_home, container, false)
         job = CoroutineScope(IO).launch {
            getVideoList()
        }


        return root
    }

    private suspend fun getVideoList()  {
        withContext(IO) {
            d(TAG, "getVideoList: video list 1 thread ${Thread.currentThread().name}")
            var exUri = ArrayList<Media>()
            var inUri = ArrayList<Media>()
            val job = async() {
               d(TAG, "getVideoList: video list 2 thread ${Thread.currentThread().name}")
                exUri = getExternalContentVideoUri(root.context)
                inUri = getInternalContentVideoUri(root.context)
                exUri.addAll(inUri)
            }
            job.await()
            setAdapter(exUri)
        }
    }

    private suspend fun setAdapter(videoList: ArrayList<Media>) {
        withContext(Main){
            d(TAG, "setAdapter: thread ${Thread.currentThread().name}")
            val sharedPreferences = root.context.getSharedPreferences(SHARED_PREF_SETTINGS, Context.MODE_PRIVATE)
            if (sharedPreferences.getBoolean(IS_GRID_LAYOUT, false)) {
                val adapter = VideoRecylerViewAdapter(videoList)
                root.video_list_recycler_view.adapter = adapter
                val gridLayoutManager = GridLayoutManager(root.context, 2)
                root.video_list_recycler_view.layoutManager = gridLayoutManager
            } else {
                val adapter = VideoRecylerViewAdapter(videoList)
                root.video_list_recycler_view.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(root.context,LinearLayoutManager.VERTICAL,false)
                }
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        d(TAG, "onDestroy: job is cancel ${job.isCancelled}")
        d(TAG, "onDestroy: job is active ${job.isActive}")
        d(TAG, "onDestroy: job is completed ${job.isCompleted}")
    }
}
