package com.one4ll.xplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.strictmode.DiskWriteViolation;

import javax.xml.transform.Result;

public class DownloadActivity extends AppCompatActivity {
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        ActivityCompat.requestPermissions(DownloadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    }

    private void downloadImageFile() {
        String url ="https://images.unsplash.com/photo-1586291555476-33e6a30cd7ab?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=976&q=80";
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE|DownloadManager.Request.NETWORK_WIFI);
        request.setDescription("description");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if (requestCode ==0){
                downloadImageFile();
            }
        }
    }
}
