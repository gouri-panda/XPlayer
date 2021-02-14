package com.one4ll.xplayer.helpers

import android.content.Context
import android.graphics.Bitmap
import android.media.ThumbnailUtils
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

/**
 * Converts long(i.e in milli seconds to string)
 * @param duration the time in milli second
 * @return returns string of time
 */
fun convertDuration(duration: Long): String {
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
    return if (hours > 0) {
        "$hours:$minutes:$seconds"
    } else {
        "$minutes:$seconds"
    }
}

/**
 * Gets the all video uri of internal storage
 * @param context of the the application
 * @param projection A list of which columns to return. Passing null will
 *         return all columns, which is inefficient.
 * @param selection A filter declaring which rows to return, formatted as an
 *         SQL WHERE clause (excluding the WHERE itself). Passing null will
 *         return all rows for the given URI.
 * @param selectionArgs You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in the order that they
 *         appear in the selection. The values will be bound as Strings.
 * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
 *         clause (excluding the ORDER BY itself). Passing null will use the
 *         default sort order, which may be unordered.
 * @return the arrayList of [Media]
 */
fun getInternalContentVideoUri(context: Context,
                               projection: Array<out String?>? = null,
                               selection: String? = null,
                               selectionArgs: Array<out String?>? = null,
                               sortOrder: String? = null)
        : ArrayList<Media> {
    val videoList = ArrayList<Media>()
    val videoInternalCursor = context.contentResolver.query(
            MediaStore.Video.Media.INTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
    )
    videoInternalCursor?.let {
        while (it.moveToNext()) {
            val videoName =
                    it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DISPLAY_NAME))
            val duration =
                    it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.DURATION))
            val size =
                    it.getString(videoInternalCursor.getColumnIndex(MediaStore.Video.VideoColumns.SIZE))
            @Suppress("DEPRECATION") val path =
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

/**
 * Gets the all video uri of primary external storage
 * @param context of the the application
 * @param projection A list of which columns to return. Passing null will
 *         return all columns, which is inefficient.
 * @param selection A filter declaring which rows to return, formatted as an
 *         SQL WHERE clause (excluding the WHERE itself). Passing null will
 *         return all rows for the given URI.
 * @param selectionArgs You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in the order that they
 *         appear in the selection. The values will be bound as Strings.
 * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
 *         clause (excluding the ORDER BY itself). Passing null will use the
 *         default sort order, which may be unordered.
 * @return the arrayList of [Media]
 */
fun getExternalContentVideoUri(context: Context,
                               projection: Array<out String?>? = null,
                               selection: String? = null,
                               selectionArgs: Array<out String?>? = null,
                               sortOrder: String? = null): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //query from content resolver
    val videoExternalCursor = context.contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
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
            @Suppress("DEPRECATION") val path =
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

/**
 * Gets the all imageUri uri of primary external storage
 * @param context of the the application
 * @param projection A list of which columns to return. Passing null will
 *         return all columns, which is inefficient.
 * @param selection A filter declaring which rows to return, formatted as an
 *         SQL WHERE clause (excluding the WHERE itself). Passing null will
 *         return all rows for the given URI.
 * @param selectionArgs You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in the order that they
 *         appear in the selection. The values will be bound as Strings.
 * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
 *         clause (excluding the ORDER BY itself). Passing null will use the
 *         default sort order, which may be unordered.
 * @return the arrayList of [Media]
 */
fun getExternalContentImageUri(context: Context,
                               projection: Array<out String?>? = null,
                               selection: String? = null,
                               selectionArgs: Array<out String?>? = null,
                               sortOrder: String? = null): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val imageExternalCursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
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
            @Suppress("DEPRECATION") val path =
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

/**
 * Gets the all image uri of internal storage
 * @param projection A list of which columns to return. Passing null will
 *         return all columns, which is inefficient.
 * @param selection A filter declaring which rows to return, formatted as an
 *         SQL WHERE clause (excluding the WHERE itself). Passing null will
 *         return all rows for the given URI.
 * @param selectionArgs You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in the order that they
 *         appear in the selection. The values will be bound as Strings.
 * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
 *         clause (excluding the ORDER BY itself). Passing null will use the
 *         default sort order, which may be unordered.
 * @param context of the the application
 * @return the arrayList of [Media]
 */
fun getInternalContentImageUri(context: Context,
                               projection: Array<out String?>? = null,
                               selection: String? = null,
                               selectionArgs: Array<out String?>? = null,
                               sortOrder: String? = null): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val imageExternalCursor = context.contentResolver.query(
            MediaStore.Images.Media.INTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
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
            @Suppress("DEPRECATION") val path =
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

/**
 * Gets the all music uri of internal storage
 * @param projection A list of which columns to return. Passing null will
 *         return all columns, which is inefficient.
 * @param selection A filter declaring which rows to return, formatted as an
 *         SQL WHERE clause (excluding the WHERE itself). Passing null will
 *         return all rows for the given URI.
 * @param selectionArgs You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in the order that they
 *         appear in the selection. The values will be bound as Strings.
 * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
 *         clause (excluding the ORDER BY itself). Passing null will use the
 *         default sort order, which may be unordered.
 * @param context of the the application
 * @return the arrayList of [Media]
 */
@Suppress("unused")
fun getInternalContentMusicUri(context: Context, projection: Array<out String?>? = null,
                               selection: String? = null, selectionArgs: Array<out String?>? = null,
                               sortOrder: String? = null): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val musicExternalCursor = context.contentResolver.query(
            MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
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
            @Suppress("DEPRECATION") val path =
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

/**
 * Gets the all musicUri uri of primary external storage
 * @param context of the the application
 * @param projection A list of which columns to return. Passing null will
 *         return all columns, which is inefficient.
 * @param selection A filter declaring which rows to return, formatted as an
 *         SQL WHERE clause (excluding the WHERE itself). Passing null will
 *         return all rows for the given URI.
 * @param selectionArgs You may include ?s in selection, which will be
 *         replaced by the values from selectionArgs, in the order that they
 *         appear in the selection. The values will be bound as Strings.
 * @param sortOrder How to order the rows, formatted as an SQL ORDER BY
 *         clause (excluding the ORDER BY itself). Passing null will use the
 *         default sort order, which may be unordered.
 * @return the arrayList of [Media]
 */
@Suppress("unused")
fun getExternalContentMusicUri(context: Context,
                               projection: Array<out String?>? = null,
                               selection: String? = null,
                               selectionArgs: Array<out String?>? = null,
                               sortOrder: String? = null): ArrayList<Media> {
    val videoList = ArrayList<Media>()
    //video projection
    //query from content resolver
    val musicExternalCursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            sortOrder
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
            @Suppress("DEPRECATION") val path =
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

/**
 * Creates a thumbNail for the given video file
 * @param file the video file
 * @param imageView where we wanna set the Thumbnail
 */
@Suppress("unused")
suspend fun Context.setVideoThumbNail(file: File, imageView: ImageView) {
    setVideoThumbNail(file.canonicalPath, imageView)
}

/**
 * Creates a thumbNail for the given video file
 * @param filePath The  video filePath we wanna convert into ThumbNail
 * @param imageView where we wanna set the Thumbnail
 */
suspend fun Context.setVideoThumbNail(filePath: String, imageView: ImageView) {
    withContext(IO) {
        val bitMap: Bitmap = getBitmapThumbnailFromVideoFile(filePath)
        withContext(Main) {
            imageView.setImageBitmap(bitMap)
            Glide.with(this@setVideoThumbNail).load(bitMap).into(imageView)
        }
    }
}

private fun getBitmapThumbnailFromVideoFile(filePath: String) =
        ThumbnailUtils.createVideoThumbnail(File(filePath), Size(200, 200), CancellationSignal())

/**
 * Creates a thumbNail for the given image file
 * @param file The image file
 * @param imageView where we wanna set the Thumbnail
 */
@Suppress("unused")
suspend fun setImageThumbNail(file: File, imageView: ImageView) {
    setImageThumbNail(file.canonicalPath, imageView)
}

/**
 * Creates a thumbNail for the given image file
 * @param filePath The image filePath we wanna convert into ThumbNail
 * @param imageView where we wanna set the Thumbnail
 */
suspend fun setImageThumbNail(filePath: String, imageView: ImageView) {
    withContext(IO) {
        val bitMap: Bitmap = getBitmapThumbNailFromImageFile(filePath)
        withContext(Main) {
            Glide.with(imageView.context).load(bitMap).into(imageView)
        }
    }
}

private fun getBitmapThumbNailFromImageFile(filePath: String): Bitmap {
    return ThumbnailUtils.createImageThumbnail(File(filePath), Size(200, 200), CancellationSignal())
}

/**
 * Creates a thumbNail for the given music file
 * @param file the music file
 * @param imageView where we wanna set the Thumbnail
 */
@Suppress("unused")
suspend fun setMusicThumbNail(file: File, imageView: ImageView) {
    setMusicThumbNail(file.canonicalPath, imageView)
}

/**
 * Creates a thumbNail for the given image file
 * @param fiePath The image filePath we wanna convert into ThumbNail
 * @param imageView where we wanna set the Thumbnail
 */
@Suppress("DEPRECATION")
suspend fun setMusicThumbNail(fiePath: String, imageView: ImageView) = withContext(IO) {
    try {
        val bitMap: Bitmap? = if (IS_Q_OR_LETTER()) {
            getBitmapThumbNailFromAudioFile(fiePath)
        } else {
            ThumbnailUtils.createAudioThumbnail(File(fiePath).absolutePath, MediaStore.Images.Thumbnails.MINI_KIND)
        }
        withContext(Main) {
            Glide.with(imageView.context).load(bitMap).into(imageView)
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
//        context.contentResolver.loadThumbnail(Uri.parse(fiePath), Size(100, 100), null)

    }

}

private fun getBitmapThumbNailFromAudioFile(fiePath: String) =
        ThumbnailUtils.createAudioThumbnail(File(fiePath), Size(100, 100), null)


/**
 * Hides the system UI
 * @param toggleActionVisibility true if we wanna hide false if showUi
 */
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

/**
 * Shows the system UI
 * @param toggleActionVisibility false if we wanna hide true if showUi
 */
@Suppress("unused")
fun AppCompatActivity.showSystemUi(toggleActionVisibility: Boolean) {
    if (toggleActionVisibility) {
        supportActionBar?.show()
    }
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
}

