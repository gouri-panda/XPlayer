package com.one4ll.xplayer.helpers

import android.app.Activity
import android.content.Context
import android.content.LocusId
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

const val VIDEO_PATH = "video_path"
const val IMAGE_PATH = "image_path"
const val MUSIC_PATH = "music_path"
const val SHARED_PREF_SETTINGS = "shared_pref_settings"
const val IS_GRID_LAYOUT = "is_grid_layout"



































fun IS_MARSHMALLOW_OR_LETTER() =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun IS_NOUGHOT_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun IS_NOUGHOT_MR1_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun IS_OREO_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun IS_PIE_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
fun IS_Q_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q





fun havePermission(context: Context, permission: String) : Boolean =
    ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
fun askPermission(activity: Activity,vararg permissions : String,permissionId : Int){
    ActivityCompat.requestPermissions(activity,permissions,permissionId)
}
fun handlePermission(activity: Activity,permission: String,permissionId: Int) : Boolean{
    if (havePermission(activity.applicationContext,permission)) return true
    else askPermission(activity,permission,permissionId = permissionId)
    return false
}

fun isExternalMounted() = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
fun isExternalOnlyMounted() = Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())
const val SKIP_DURATION = 5000


