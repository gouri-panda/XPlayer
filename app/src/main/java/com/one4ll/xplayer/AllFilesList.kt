package com.one4ll.xplayer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_all_files_list.*
import java.io.File
import java.lang.Exception
private const val READ_AND_WRITE_STORAGE_PERMISSION = 3
class AllFilesList : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private var thumbnail = File("")
    private lateinit  var recylerViewAdapter : RecylerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_files_list)
        thumbnail.delete()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        val videoList = ArrayList<Video>()
         recylerViewAdapter = RecylerViewAdapter(videoList)
        video_list_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        video_list_recycler_view.adapter = recylerViewAdapter
        if (readAndWriteExternalStoragePermission()){
            getVideoList()
        }



    }

    private fun getVideoList() {
        val externalVideoList = getExternalContentVidoUri()
        val intenalVideoList = getInternalContentVidoUri()
        externalVideoList.addAll(intenalVideoList)
        recylerViewAdapter.loadVideo(externalVideoList)
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
                val duration =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION))
                val size =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE))
                var path =
                        it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.Media.DATA))
                Log.d(TAG, "getExternalContentVidoUri: path $path")
                try {
                    var bitMap: Bitmap? = null
                    bitMap = ThumbnailUtils.createVideoThumbnail(
                            path,
                            MediaStore.Video.Thumbnails.MINI_KIND
                    )

                    bitMap?.let {
                        val video = Video(videoName, duration.toInt().toString(), size, bitMap!!,path)
                        videoList.add(video)
                    }
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
                val duration =
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
                    val video = Video(videoName, duration, size, bitmap,path)

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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
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

}