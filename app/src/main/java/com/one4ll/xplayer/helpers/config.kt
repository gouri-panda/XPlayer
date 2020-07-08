package com.one4ll.xplayer.helpers

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun IS_MARSHMALLOW_OR_LETTER() =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun IS_NOUGHOT_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun IS_NOUGHOT_MR1_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun IS_OREO_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun IS_PIE_OR_LETTER() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P






fun isHavePermission(context: Context,permission: String) : Boolean =
    ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
fun askPermission(activity: Activity,vararg permissions : String,requestCode : Int){
    ActivityCompat.requestPermissions(activity,permissions,requestCode)
}

fun isExternalMounted() = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
fun isExternalOnlyMounted() = Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())


