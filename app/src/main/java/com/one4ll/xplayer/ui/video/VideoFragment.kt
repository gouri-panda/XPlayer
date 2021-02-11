package com.one4ll.xplayer.ui.video

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.VideoRecyclerViewAdapter
import com.one4ll.xplayer.helpers.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

private const val TAG = "homeFragment"

// TODO how to set permission with Android view model
class VideoFragment : Fragment() {
    private val   videoViewModel: VideoViewModel by viewModels()
    private lateinit var root: View


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            askPermissionForVideoList()
        }
        val brightNess = Settings.System.getInt(root.context.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
        d(TAG, "onScroll: brightness $brightNess")
    }

    //we will ask  once for videos ,images and audios
    private suspend fun askPermissionForVideoList() {
        if (IS_MARSHMALLOW_OR_LETTER()) {
            if (havePermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                getVideoList()
            } else {
                //ask permission nicely!!
                activity?.let {
                    askPermission(activity = it,
                            permissions = *arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            permissionId = 2)
                }
            }
        } else {
            getVideoList()
        }

    }

    private suspend fun getVideoList() {
        withContext(IO) {
            val externalContentVideoJob: Deferred<ArrayList<Media>> = async { getExternalContentVideoUri(root.context) }
            val internalContentVideoJob: Deferred<ArrayList<Media>> = async { getInternalContentVideoUri(root.context) }
            val contentUri = externalContentVideoJob.await() + internalContentVideoJob.await()
            setAdapter(contentUri)
        }
    }

    private suspend fun setAdapter(videoList: List<Media>) {
        withContext(Main) {
            d(TAG, "setAdapter: thread ${Thread.currentThread().name}")
            val sharedPreferences = root.context.getSharedPreferences(SHARED_PREF_SETTINGS, Context.MODE_PRIVATE)
            if (sharedPreferences.getBoolean(IS_GRID_LAYOUT, false)) {
                val adapter = activity?.let { VideoRecyclerViewAdapter(it, videoList) }
                root.video_list_recycler_view.adapter = adapter
                val gridLayoutManager = GridLayoutManager(root.context, 2)
                root.video_list_recycler_view.layoutManager = gridLayoutManager
            } else {
                val adapter = activity?.let { VideoRecyclerViewAdapter(it, videoList) }
                root.video_list_recycler_view.apply {
                    this.adapter = adapter
                    layoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
                }
            }


        }
    }

}
