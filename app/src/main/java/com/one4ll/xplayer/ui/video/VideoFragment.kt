package com.one4ll.xplayer.ui.video

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.compose.MediaCard
import com.one4ll.xplayer.helpers.*
import kotlinx.android.synthetic.main.fragment_video.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

private const val TAG = "homeFragment"
private const val STORAGE_PERMISSION = 2

// TODO how to set permission with Android view model??
// TODO add grid or linear layout
class VideoFragment : Fragment() {
    private var videoUriLists = emptyList<Media>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LazyColumn {
                    items(videoUriLists) { videoUri ->
                        MediaCard(media = videoUri) { path ->
                            var bitmap: Bitmap? = null
                            lifecycleScope.launch {
                                withContext(IO) {
                                    bitmap = createBitmapThumbnailFromVideoFile(path)
                                }
                            }
                            return@MediaCard bitmap

                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        askPermissionForVideoList()
    }

//we will ask  once for videos ,images and audios
    /**
     * Asks permission about read and write  if The device is below marshmallow then  no need to ask
     * we already have permission
     */
    private fun askPermissionForVideoList() {
        if (IS_MARSHMALLOW_OR_LETTER()) {
            if (havePermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                lifecycleScope.launch { getVideoList() }
            } else {
//                ask permission nicely!!
                activity?.let { activity ->
                    askPermission(
                        activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_SETTINGS,
                        permissionId = STORAGE_PERMISSION
                    )
                }
            }
        } else {
            lifecycleScope.launch { getVideoList() }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        d(TAG, "onRequestPermissionsResult: ")
        if (requestCode == STORAGE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                lifecycleScope.launch { getVideoList() }
            }
        }
    }

    /**
     * Gets the all video list from internal storage and external storage
     * then it stores in the database
     */
    private suspend fun getVideoList() {
        withContext(IO) {
            val externalContentVideoJob: Deferred<ArrayList<Media>> =
                async { getExternalContentVideoUri(requireContext()) }
            val internalContentVideoJob: Deferred<ArrayList<Media>> =
                async { getInternalContentVideoUri(requireContext()) }
            videoUriLists = externalContentVideoJob.await() + internalContentVideoJob.await()

        }
    }

    /**
     * Sets the adapter
     */
//todo add grid layout or linear layout in compose
//    private suspend fun setAdapter(videoList: List<Media>) {
//        withContext(Main) {
//            d(TAG, "setAdapter: thread ${Thread.currentThread().name}")
//            val sharedPreferences = binding.root.context.getSharedPreferences(
//                SHARED_PREF_SETTINGS,
//                Context.MODE_PRIVATE
//            )
//            if (sharedPreferences.getBoolean(IS_GRID_LAYOUT, false)) {
//                val adapter =
//                    activity?.let { VideoRecyclerViewAdapter(it, videoList, lifecycleScope) }
//                binding.root.video_list_recycler_view.adapter = adapter
//                val gridLayoutManager = GridLayoutManager(binding.root.context, 2)
//                binding.root.video_list_recycler_view.layoutManager = gridLayoutManager
//            } else {
//                setc
//                LazyColumn {
//
//                }
//                MediaCard(media = videoList)
//            }
//        }
//    }

}
