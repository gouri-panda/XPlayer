package com.one4ll.xplayer.helpers

import android.content.Context
import android.provider.MediaStore
import com.one4ll.xplayer.Video

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
fun getInternalContentVidoUri(context: Context): ArrayList<Video> {
    val videoList = ArrayList<Video>()
    var videoProjection = arrayOf(
            MediaStore.Video.VideoColumns.ALBUM,
            MediaStore.Video.VideoColumns.DATE_ADDED,
            MediaStore.Video.VideoColumns.DISPLAY_NAME,
            MediaStore.Video.VideoColumns.DURATION,
            MediaStore.Video.VideoColumns.SIZE,
            MediaStore.Video.VideoColumns.DATA
    )
    var videoInternalCursor = context.contentResolver.query(
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
//                    val bitmap = ThumbnailUtils.createVideoThumbnail(
//                            path,
//                            MediaStore.Video.Thumbnails.MINI_KIND
//                    )
                val n = convertDuration(duration.toLong())
                val video = Video(videoName, n.toString(), size,path)

                videoList.add(video)
            } catch (e: Exception) {
            }
        }
    }
    videoInternalCursor?.close()
    return videoList
}
 fun getExternalContentVidoUri(context: Context): ArrayList<Video> {
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
    var videoExternalCursor = context.contentResolver.query(
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
            try {
                val n  = convertDuration(duration.toLong())
                val video = Video(videoName, n.toString(), size,path)

                videoList.add(video)
            } catch (e: Exception) {
            }

        }
    }
    videoExternalCursor?.close()
    return videoList
}