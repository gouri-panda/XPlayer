package com.one4ll.xplayer.helpers

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.CancellationSignal
import android.provider.MediaStore
import android.util.Size
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.one4ll.xplayer.Media
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.io.File

fun convertDuration(duration: Long): String {
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

fun getInternalContentVideoUri(context: Context): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    val videoProjection = arrayOf(
            MediaStore.Video.VideoColumns.ALBUM,
            MediaStore.Video.VideoColumns.DATE_ADDED,
            MediaStore.Video.VideoColumns.DISPLAY_NAME,
            MediaStore.Video.VideoColumns.DURATION,
            MediaStore.Video.VideoColumns.SIZE,
            MediaStore.Video.VideoColumns.DATA
    )
    val videoInternalCursor = context.contentResolver.query(
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
//                    val bitmap = ThumbnailUtils.createVideoThumbnail(
//                            path,
//                            MediaStore.Video.Thumbnails.MINI_KIND
//                    )
                val n = convertDuration(duration.toLong())
                val video = Media(videoName, n, size, path)

                videoList.add(video)
            } catch (e: Exception) {
            }
        }
    }
    videoInternalCursor?.close()
    return videoList
}

fun getExternalContentVideoUri(context: Context): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    val videoProjection = arrayOf(
            MediaStore.Video.VideoColumns.ALBUM,
            MediaStore.Video.VideoColumns.DATE_ADDED,
            MediaStore.Video.VideoColumns.DISPLAY_NAME,
            MediaStore.Video.VideoColumns.DURATION,
            MediaStore.Video.VideoColumns.SIZE,
            MediaStore.Video.VideoColumns.DATA
    )
    //query from content resolver
    val videoExternalCursor = context.contentResolver.query(
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
            val path =
                    it.getString(videoExternalCursor.getColumnIndex(MediaStore.Video.Media.DATA))
            try {
                val n = convertDuration(duration.toLong())
                val video = Media(videoName, n, size, path)

                videoList.add(video)
            } catch (e: Exception) {
            }

        }
    }
    videoExternalCursor?.close()
    return videoList
}

fun getExternalContentImageUri(context: Context): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val imageExternalCursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
    )

    //iterating cursor to get Video list
    imageExternalCursor?.let {
        while (it.moveToNext()) {
            val videoName =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
            val duration =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN))
            val size =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.SIZE))
            val path =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
            try {
                val n = convertDuration(duration.toLong())
                val video = Media(videoName, n, size, path)

                videoList.add(video)
            } catch (e: Exception) {
            }

        }
    }
    imageExternalCursor?.close()
    return videoList
}

fun getInternalContentImageUri(context: Context): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val imageExternalCursor = context.contentResolver.query(
            MediaStore.Images.Media.INTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
    )

    //iterating cursor to get Video list
    imageExternalCursor?.let {
        while (it.moveToNext()) {
            val videoName =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME))
            val duration =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN))
            val size =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.SIZE))
            val path =
                    it.getString(imageExternalCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
            try {
                val n = convertDuration(duration.toLong())
                val video = Media(videoName, n, size, path)

                videoList.add(video)
            } catch (e: Exception) {
            }

        }
    }
    imageExternalCursor?.close()
    return videoList
}

fun getInternalContentMusicUri(context: Context): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val musicExternalCursor = context.contentResolver.query(
            MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
    )

    //iterating cursor to get Video list
    musicExternalCursor?.let {
        while (it.moveToNext()) {
            val videoName =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME))
            val duration =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION))
            val size =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE))
            val path =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA))
            try {
                val n = convertDuration(duration.toLong())
                val video = Media(videoName, n, size, path)

                videoList.add(video)
            } catch (e: Exception) {
            }

        }
    }
    musicExternalCursor?.close()
    return videoList
}

fun getExternalContentMusicUri(context: Context): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val musicExternalCursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            null
    )

    //iterating cursor to get Video list
    musicExternalCursor?.let {
        while (it.moveToNext()) {
            val videoName =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DISPLAY_NAME))
            val duration =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION))
            val size =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE))
            val path =
                    it.getString(musicExternalCursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA))
            try {
                val n = convertDuration(duration.toLong())
                val video = Media(videoName, n, size, path)

                videoList.add(video)
            } catch (e: Exception) {
            }

        }
    }
    musicExternalCursor?.close()
    return videoList
}

suspend fun Context.setVideoThumbNail(filePath: String, imageView: ImageView) {
    withContext(IO) {
        val bitMap: Bitmap = ThumbnailUtils.createVideoThumbnail(File(filePath), Size(200, 200), CancellationSignal())
        withContext(Main) {
            imageView.setImageBitmap(bitMap)
            Glide.with(this@setVideoThumbNail).load(bitMap).into(imageView)
        }
    }
}

suspend fun setImageThumbNail(filePath: String, imageView: ImageView) {
    withContext(IO) {
        val bitMap: Bitmap = ThumbnailUtils.createImageThumbnail(File(filePath), Size(200, 200), CancellationSignal())
        withContext(Main) {
            Glide.with(imageView.context).load(bitMap).into(imageView)
        }
    }
}

//todo remove coroutine scope and add suspend
suspend fun setMusicThumbNail(context: Context, fiePath: String, imageView: ImageView) = withContext(IO) {
    try {
        val bitMap: Bitmap? = if (IS_Q_OR_LETTER()) {
            ThumbnailUtils.createAudioThumbnail(File(fiePath), Size(100, 100), null)
        } else {
            ThumbnailUtils.createAudioThumbnail(File(fiePath).absolutePath, MediaStore.Images.Thumbnails.MINI_KIND)
        }
        withContext(Main) {
            imageView.setImageBitmap(bitMap)
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        context.contentResolver.loadThumbnail(Uri.parse(fiePath), Size(100, 100), null)

    }

}

fun AppCompatActivity.hideSystemUI(toggleActionVisibility: Boolean) {
    if (toggleActionVisibility) {
        supportActionBar?.hide()
    }
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE
}

fun AppCompatActivity.showSystemUi(toggleActionVisibility: Boolean) {
    if (toggleActionVisibility) {
        supportActionBar?.show()
    }
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

