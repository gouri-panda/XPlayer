package com.one4ll.xplayer

import android.Manifest
import android.content.pm.PackageManager
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.helpers.IS_MARSHMALLOW_OR_LETTER
import com.one4ll.xplayer.models.Medium
import kotlinx.android.synthetic.main.activity_all_files_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import java.io.File

private const val READ_AND_WRITE_STORAGE_PERMISSION = 3
class AllFilesList : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private var thumbnail = File("")
    private lateinit  var recylerViewAdapter : RecylerViewAdapter
    private lateinit var mediaDatabase: MediaDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_files_list)
        thumbnail.delete()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val videoList = ArrayList<Video>()
         recylerViewAdapter = RecylerViewAdapter(videoList)
        video_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        video_list_recycler_view.adapter = recylerViewAdapter
         mediaDatabase  = MediaDatabase.getInstance(this)
        if (readAndWriteExternalStoragePermission()){
            getVideoList()
        }


    }

    private  fun getVideoList() {
        val externalVideoList = getExternalContentVidoUri()
        val internalVideoList = getInternalContentVidoUri()
        externalVideoList.addAll(internalVideoList)
        recylerViewAdapter.loadVideo(externalVideoList)
        CoroutineScope(IO).async {
            async {
                var count = 0
                //todo fix  - remove delete all and fix auto increment id
                mediaDatabase.mediumDao().deleteAll()
            externalVideoList.forEach {
                val medium = Medium(count++,it.name,it.path,it.duration)
                mediaDatabase.mediumDao().insert(medium)
            }}.await().run {
                Log.d(TAG, "after update data base")
                mediaDatabase.mediumDao().getAll().forEach {
                    Log.d(TAG, "form media data base ${it.toString()}")

                }
            }

        }


    }
    private fun getExternalContentVidoUri(): ArrayList<Video> {
        val videoList = ArrayList<Video>()
        //video projection
        var videoProjection = arrayOf(
                MediaStore.Video.VideoColumns.ALBUM,
                MediaStore.Video.VideoColumns.DATE_ADDED,
                MediaStore.Video.VideoColumns.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.DURATION,
                MediaStore.Video.VideoColumns.SIZE,
                MediaStore.Video.VideoColumns.DATA
        )
        //query from content resolver
        var videoExternalCursor = contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                videoProjection,
                null,
                null,
                null
        )

        //iterating cursor to get Video list
        videoExternalCursor?.let {
            while (it.moveToNext()) {
                val videoName =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME))
                var duration =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION))
                val size =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE))
                var path =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.Media.DATA))
                Log.d(TAG, "getExternalContentVidoUri: path $path")
                try {
                        val n  = convertDuration(duration.toLong())
                        val video = Video(videoName, n.toString(), size,path)

                        videoList.add(video)
                } catch (e: Exception) {
                    Log.e(TAG, "getExternalContentVidoUri: ${e.message}");
                }

            }
        }
        videoExternalCursor?.close()
        return videoList
    }

    private fun getInternalContentVidoUri(): ArrayList<Video> {
        val videoList = ArrayList<Video>()
        var videoProjection = arrayOf(
                MediaStore.Video.VideoColumns.ALBUM,
                MediaStore.Video.VideoColumns.DATE_ADDED,
                MediaStore.Video.VideoColumns.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.DURATION,
                MediaStore.Video.VideoColumns.SIZE,
                MediaStore.Video.VideoColumns.DATA
        )
        var videoInternalCursor = contentResolver.query(
                MediaStore.Video.Media.INTERNAL_CONTENT_URI,
                videoProjection,
                null,
                null,
                null
        )
        videoInternalCursor?.let {
            while (it.moveToNext()) {
                val videoName =
                        it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME))
                var duration =
                        it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION))
                val size =
                        it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE))
                val path =
                        it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA))
                try {
                    val bitmap = ThumbnailUtils.createVideoThumbnail(
                            path,
                            MediaStore.Video.Thumbnails.MINI_KIND
                    )
                    val n = convertDuration(duration.toLong())
                    val video = Video(videoName, n.toString(), size,path)

                    videoList.add(video)
                } catch (e: Exception) {
                }

                Log.d(TAG, "onCreate: video uri video name $videoName")
                Log.d(TAG, "onCreate: video uri video duration $duration")
                Log.d(TAG, "onCreate: video uri video size $size")
            }
        }
        videoInternalCursor?.close()
        return videoList
    }

    private fun readAndWriteExternalStoragePermission() : Boolean{
        if (IS_MARSHMALLOW_OR_LETTER()){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_AND_WRITE_STORAGE_PERMISSION)
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            READ_AND_WRITE_STORAGE_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getVideoList()
                }
            }
        }

    }

    private fun convertDuration(duration: Long): String {
        var out: String? = null
        var hours: Long = 0
        try {
            hours = duration / 3600000
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val remainingMinutes = (duration - hours * 3600000) / 60000
        var minutes = remainingMinutes.toString()
        if (minutes == "0") {
            minutes = "00"
        }
        val remainingSecs = duration - hours * 3600000 - remainingMinutes * 60000
        var seconds = remainingSecs.toString()
        seconds = if (seconds.length < 2) {
            "00"
        } else {
            seconds.substring(0, 2)
        }
        out = if (hours > 0) {
            "$hours:$minutes:$seconds"
        } else {
            "$minutes:$seconds"
        }
        return out
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaDatabase.close()
    }

}
interface OnSaveDataInDataBase{
    fun  onDatabased()
}