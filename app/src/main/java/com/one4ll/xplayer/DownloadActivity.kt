package com.one4ll.xplayer

import android.Manifest
import android.app.DownloadManager
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class DownloadActivity : AppCompatActivity() {
    var downloadManager: DownloadManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        ActivityCompat.requestPermissions(this@DownloadActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
    }

    private fun downloadImageFile() {
        val url = "https://images.unsplash.com/photo-1586291555476-33e6a30cd7ab?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=976&q=80"
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setVisibleInDownloadsUi(true)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setDescription("description")
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image")
        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadManager!!.enqueue(request)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 0) {
                downloadImageFile()
            }
        }
    }
}