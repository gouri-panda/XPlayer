package com.one4ll.xplayer;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.main_activity_constraint_layout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.player_view)
    PlayerView playerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.image_button_play)
    ImageButton playButton;
    @BindView(R.id.image_button_pause)
    ImageButton pauseButton;
    @BindView(R.id.image_button_play_2)
    ImageButton playButton2;
    @BindView(R.id.image_button_pause_2)
    ImageButton pauseButton2;
    @BindView(R.id.imageButtonPrevious)
    ImageButton imageButtonPrevious;
    @BindView(R.id.imageButtonNext)
    ImageButton imageButtonNext;

    private SimpleExoPlayer simpleExoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        simpleExoPlayer = new SimpleExoPlayer.Builder(MainActivity.this).build();
        playerView.setPlayer(simpleExoPlayer);
        final Uri fileUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        Uri fileUri2 = Uri.parse("https://www.videvo.net/videvo_files/converted/2018_01/preview/171124_H1_005.mp436952.webm");
        Uri fileUri3 = Uri.parse("http://185.105.103.101/serial/Supernatural/S13/720p.x265/Supernatural.S13E07.720p.HDTV.2CH.x265.HEVC.Filmaneh.mkv");
        if (fileUri.isAbsolute()) {
            Log.d(TAG, "onCreate: file uri is absolute");
        }
        Log.d(TAG, "onCreate: app name " + getString(R.string.app_name));
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(MainActivity.this, Util.getUserAgent(MainActivity.this, getString(R.string.app_name)));
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(fileUri2);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.addAudioListener(audioListener);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(eventListener);
        calculateAndGetUriAndFolders();
         LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter("customIntent"));
//         new TestingLocalBroadCast().execute();

    }
    private  BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: " + intent.getStringExtra("message"));
        }
    };

    @OnClick(R.id.image_button_play)
    public void play() {
        playVideo();
    }

    @OnClick(R.id.image_button_play_2)
    public void play2() {
        playVideo();
    }

    private void playVideo() {
        Log.d(TAG, "play: clicked");
        if (simpleExoPlayer.getPlaybackState() == Player.STATE_ENDED) {
            simpleExoPlayer.seekTo(0);
        }
        simpleExoPlayer.setPlayWhenReady(true);
        playButton.setVisibility(View.INVISIBLE);
        playButton2.setVisibility(View.INVISIBLE);
        pauseButton2.setVisibility(View.VISIBLE);

    }


    @OnClick(R.id.player_view)
    public void onPlayViewClicked() {
        pauseButton.setVisibility(View.VISIBLE);
        pauseButton2.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.image_button_pause)
    public void pause() {
        pauseVideo();
    }

    @OnClick(R.id.image_button_pause_2)
    public void pause2() {
        pauseVideo();
    }

    @OnClick(R.id.imageButtonPrevious)
    public void goToPrevious() {
        int state = simpleExoPlayer.getPlaybackState();
    }

    private void pauseVideo() {
        progressBar.setVisibility(View.GONE);
        simpleExoPlayer.setPlayWhenReady(false);
        pauseButton.setVisibility(View.INVISIBLE);
        pauseButton2.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.VISIBLE);
        playButton2.setVisibility(View.VISIBLE);

    }


    private Player.EventListener eventListener = new Player.EventListener() {
        @Override
        public void onTimelineChanged(Timeline timeline, int reason) {
            Log.d(TAG, "onTimelineChanged: timeline " + timeline.toString());
            Log.d(TAG, "onTimelineChanged: reason " + reason);
        }

        @Override
        public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {
            Log.d(TAG, "onTimelineChanged: timeline " + timeline);
            Log.d(TAG, "onTimelineChanged: reason " + reason);

        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            switch (playbackState) {
                case Player.STATE_READY:
                    Log.d(TAG, "onPlayerStateChanged: state ready");
                    progressBar.setVisibility(View.INVISIBLE);
                    break;
                case Player.STATE_BUFFERING:
                    progressBar.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onPlayerStateChanged: state buffering");
                    break;
                case Player.STATE_ENDED:
                    Log.d(TAG, "onPlayerStateChanged: state Ended");
                    playButton.setVisibility(View.VISIBLE);
                    break;

            }

        }

        @Override
        public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {

        }

        @Override
        public void onIsPlayingChanged(boolean isPlaying) {
            Log.d(TAG, "onIsPlayingChanged: isPlaying" + isPlaying);
            if (isPlaying) {
                progressBar.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onRepeatModeChanged(int repeatMode) {

        }

        @Override
        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            error.printStackTrace();
            Log.d(TAG, "onPlayerError:" + error.getMessage());
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPositionDiscontinuity(int reason) {

        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }

        @Override
        public void onSeekProcessed() {

        }
    };
    private AudioListener audioListener = new AudioListener() {
        @Override
        public void onAudioSessionId(int audioSessionId) {
            Log.d(TAG, "onAudioSessionId: " + audioSessionId);
        }

        @Override
        public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes content type" + audioAttributes.contentType);
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes flags" + audioAttributes.flags);
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes usage" + audioAttributes.usage);
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes allowed capture policy" + audioAttributes.allowedCapturePolicy);


        }

        @Override
        public void onVolumeChanged(float volume) {
            Log.d(TAG, "onVolumeChanged: " + volume);
        }
    };

    @Override
    protected void onDestroy() {
        simpleExoPlayer.release();
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setTime(long timeInMills) {
        String timeString;
        int hour = -1;
        int minutes = -1;
        int secs = -1;
        hour = (int) TimeUnit.MILLISECONDS.toHours(timeInMills);
        minutes = (int) TimeUnit.MILLISECONDS.toMinutes(timeInMills);
        secs = (int) TimeUnit.MILLISECONDS.toSeconds(timeInMills);


        Log.d(TAG, "pauseVideo: duration " + timeInMills);
        Log.d(TAG, "onCreate: hour " + hour);
        Log.d(TAG, "onCreate: minute " + minutes);
        Log.d(TAG, "onCreate: time" + secs);
        if (hour != 0 && minutes != 0 && secs != 0) {
            String stringHour = String.valueOf(hour);
            String stringMinutes = String.valueOf(minutes);
            String stringSeconds = String.valueOf(secs);
            timeString = stringHour + stringMinutes + stringSeconds;
            Log.d(TAG, "setTime: " + timeString);

        } else if (minutes != 0 && secs != 0) {
            String stringMinutes = String.valueOf(minutes);
            String stringSeconds = String.valueOf(secs);
            timeString = stringMinutes + stringSeconds;
            Log.d(TAG, "setTime: " + timeString);

        } else {
            String stringSeconds = String.valueOf(secs);
            timeString = stringSeconds;
            Log.d(TAG, "setTime: " + timeString);

        }
        DateFormat dateFormat = new SimpleDateFormat("HH 'hours' ,mm 'mins',ss 'secs' ");
        Log.d(TAG, "setTime: date format" + dateFormat.format(new Date(timeInMills)));
    }

    private void calculateAndGetUriAndFolders() {
        String[] projection = {MediaStore.Video.Media._ID, MediaStore.Video.Media.ALBUM, MediaStore.Video.Media.DATE_ADDED, MediaStore.Video.Media.CONTENT_TYPE};
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.INTERNAL_CONTENT_URI,null,null,null,null );
        if (cursor == null){
            Log.d(TAG, "calculateAndGetUriAndFolders: cursror is null");
        }
        while (cursor != null && cursor.moveToFirst()){
           String  stringUri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME.toString()));
            Log.d(TAG, "calculateAndGetUriAndFolders: string uri" + stringUri);
        }
        if (cursor != null){
            cursor.close();
        }

    }


    //Stupid timing set
//    private void setTime(long timeInMils){
//        int hour  = -1;
//        int minute = -1;
//        int sec = -1;
//
//        if (timeInMils > 3600000){
//            hour =(int) timeInMils / 3600000;
//            minute =(int) timeInMils / 360000;
//            sec  =(int) timeInMils /1000;
//        }else if (timeInMils < 3600000 && timeInMils > 360000){
//            minute =(int) (timeInMils / 1000 ) / 60;

//            sec  =(int) (timeInMils /360000) % 60;
//        }else {
//            sec  =(int) (timeInMils /360000) % 60;
//        }
//        if (hour != -1){
//            if (minute != -1){
//                if (sec != -1){
//                    Log.d(TAG, "setTime: hour" + hour +"minute"+ minute + "sec" + sec );
//                }
//            }
//        }else if (minute != -1 && sec != -1){
//            Log.d(TAG, "setTime: minute" + minute + "sec " + sec);
//        }else{
//            Log.d(TAG, "setTime: sec" + sec);
//        }
//    }

}
