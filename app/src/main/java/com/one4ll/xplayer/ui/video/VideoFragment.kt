package com.one4ll.xplayer.ui.video

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
import com.one4ll.xplayer.RecylerViewAdapter
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class VideoFragment : Fragment() {
private val TAG = "homefragment"
    private lateinit var videoViewModel: VideoViewModel
    private lateinit var root : View


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        videoViewModel =
                ViewModelProviders.of(this).get(VideoViewModel::class.java)
         root = inflater.inflate(R.layout.fragment_home, container, false)
        runBlocking {
            getVideoList()
        }



        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private suspend fun getVideoList() = CoroutineScope(IO).launch {
        Log.d(TAG, "getVideoList: video list 1 thread ${Thread.currentThread().name}")
        var exUri = ArrayList<Media>()
        var inUri = ArrayList<Media>()
        val job = async() {
        Log.d(TAG, "getVideoList: video list 2 thread ${Thread.currentThread().name}")
             exUri =  getExternalContentVideoUri(root.context)
             inUri = getInternalContentVideoUri(root.context)
            exUri.addAll(inUri)
        }
        job.await()
        setAdapter(exUri)

    }
    private suspend fun setAdapter(videoList : ArrayList<Media>) = withContext(Main){
        Log.d(TAG, "getVideoList: set adapter thread ${Thread.currentThread().name}")

        Log.d(TAG, "onCreateView: exsize ${videoList.size}")
        val adapter = RecylerViewAdapter(videoList)
        root.video_list_recycler_view.adapter = adapter
        val lineaLayoutManager = LinearLayoutManager(root.context,LinearLayoutManager.VERTICAL,false)
        root.video_list_recycler_view.layoutManager = lineaLayoutManager
    }
}