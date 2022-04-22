package com.one4ll.xplayer.ui.video

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.compose.MediaList
import com.one4ll.xplayer.helpers.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                MediaList(items = videoUriLists, onClickMediaItems = { media ->
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra(VIDEO_PATH, media.path)
                    context.startActivity(intent)
                }, onClickMenuItem = {})
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        askPermissionForVideoList()
    }

//  We will ask  once for videos ,images and audios
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

}
