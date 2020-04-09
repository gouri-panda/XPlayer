package com.one4ll.xplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaDataSource;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.BlockingDeque;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AllFilesList extends AppCompatActivity {
    private static final String TAG = "AllFilesList";
    MediaProjectionManager mediaProjectionManager;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_files_list);
        mediaProjectionManager = (MediaProjectionManager) getSystemService(MEDIA_PROJECTION_SERVICE);
        button = findViewById(R.id.image_button_pause);
        button.setOnClickListener((view) ->
                Log.d(TAG, "onCreate: "));
        Observable.just("1", "2", "3")
                .subscribe(

                );

    }

}
