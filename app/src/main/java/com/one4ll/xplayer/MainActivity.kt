package com.one4ll.xplayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.audio.AudioListener
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.helpers.VIDEO_PATH
import java.io.File
import kotlin.time.milliseconds

class MainActivity : AppCompatActivity() , View.OnTouchListener, GestureDetector.OnDoubleTapListener ,GestureDetector.OnGestureListener {
    @BindView(R.id.main_activity_constraint_layout)
    var constraintLayout: ConstraintLayout? = null
    private lateinit var gestureDetector: GestureDetector

    @BindView(R.id.player_view)
    var playerView: PlayerView? = null
    private var simpleExoPlayer: SimpleExoPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        playerView = findViewById(R.id.player_view)
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        gestureDetector = GestureDetector(this,this)
        playerView?.setPlayer(simpleExoPlayer)
        val intent = intent
        val videoUriPath = intent.getStringExtra(VIDEO_PATH)
        Log.d(TAG, "onCreate: app name " + getString(R.string.app_name))
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this, Util.getUserAgent(this@MainActivity, getString(R.string.app_name)))
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.fromFile(File(videoUriPath)))
        simpleExoPlayer!!.prepare(mediaSource)
        simpleExoPlayer!!.addAudioListener(audioListener)
        simpleExoPlayer!!.playWhenReady = true
        simpleExoPlayer!!.addListener(eventListener)
        playerView?.setOnTouchListener(this)
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, IntentFilter("customIntent"))
    }

    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(TAG, "onReceive: " + intent.getStringExtra("message"))
        }
    }

    //    @OnClick(R.id.image_button_play)
    //    public void play() {
    //        playVideo();
    //    }
    //    @OnClick(R.id.image_button_play_2)
    //    public void play2() {
    //        playVideo();
    //    }
    //    private void playVideo() {
    //        Log.d(TAG, "play: clicked");
    //        if (simpleExoPlayer.getPlaybackState() == Player.STATE_ENDED) {
    //            simpleExoPlayer.seekTo(0);
    //        }
    //        simpleExoPlayer.setPlayWhenReady(true);
    //        playButton.setVisibility(View.INVISIBLE);
    //        playButton2.setVisibility(View.INVISIBLE);
    //        pauseButton2.setVisibility(View.VISIBLE);
    //
    //    }
    //    @OnClick(R.id.player_view)
    //    public void onPlayViewClicked() {
    //        pauseButton.setVisibility(View.VISIBLE);
    //        pauseButton2.setVisibility(View.VISIBLE);
    //    }
    //    @OnClick(R.id.image_button_pause)
    //    public void pause() {
    //        pauseVideo();
    //    }
    //    @OnClick(R.id.image_button_pause_2)
    //    public void pause2() {
    //        pauseVideo();
    //    }
    //    @OnClick(R.id.imageButtonPrevious)
    //    public void goToPrevious() {
    //        int state = simpleExoPlayer.getPlaybackState();
    //    }
    //    private void pauseVideo() {
    //        progressBar.setVisibility(View.GONE);
    //        simpleExoPlayer.setPlayWhenReady(false);
    //        pauseButton.setVisibility(View.INVISIBLE);
    //        pauseButton2.setVisibility(View.INVISIBLE);
    //        playButton.setVisibility(View.VISIBLE);
    //        playButton2.setVisibility(View.VISIBLE);
    //
    //    }
    private val eventListener: Player.EventListener = object : Player.EventListener {
        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            Log.d(TAG, "onTimelineChanged: timeline $timeline")
            Log.d(TAG, "onTimelineChanged: reason $reason")
        }

        override fun onTimelineChanged(timeline: Timeline, manifest: Any?, reason: Int) {
            Log.d(TAG, "onTimelineChanged: timeline $timeline")
            Log.d(TAG, "onTimelineChanged: reason $reason")
        }

        override fun onTracksChanged(trackGroups: TrackGroupArray, trackSelections: TrackSelectionArray) {}
        override fun onLoadingChanged(isLoading: Boolean) {
//            if (isLoading) {
//                progressBar.setVisibility(View.VISIBLE);
//            } else {
//                progressBar.setVisibility(View.GONE);
//            }
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//            switch (playbackState) {
//                case Player.STATE_READY:
//                    Log.d(TAG, "onPlayerStateChanged: state ready");
//                    progressBar.setVisibility(View.INVISIBLE);
//                    break;
//                case Player.STATE_BUFFERING:
//                    progressBar.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "onPlayerStateChanged: state buffering");
//                    break;
//                case Player.STATE_ENDED:
//                    Log.d(TAG, "onPlayerStateChanged: state Ended");
//                    playButton.setVisibility(View.VISIBLE);
//                    break;
//
//            }
        }

        override fun onPlaybackSuppressionReasonChanged(playbackSuppressionReason: Int) {}
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            Log.d(TAG, "onIsPlayingChanged: isPlaying$isPlaying")
            //            if (isPlaying) {
//                progressBar.setVisibility(View.INVISIBLE);
//            }
        }

        override fun onRepeatModeChanged(repeatMode: Int) {}
        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}
        override fun onPlayerError(error: ExoPlaybackException) {
            error.printStackTrace()
            Log.d(TAG, "onPlayerError:" + error.message)
            Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
        }

        override fun onPositionDiscontinuity(reason: Int) {}
        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {}
        override fun onSeekProcessed() {}
    }
    private val audioListener: AudioListener = object : AudioListener {
        override fun onAudioSessionId(audioSessionId: Int) {
            Log.d(TAG, "onAudioSessionId: $audioSessionId")
        }

        override fun onAudioAttributesChanged(audioAttributes: AudioAttributes) {
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes content type" + audioAttributes.contentType)
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes flags" + audioAttributes.flags)
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes usage" + audioAttributes.usage)
            Log.d(TAG, "onAudioAttributesChanged: audioAttributes allowed capture policy" + audioAttributes.allowedCapturePolicy)
        }

        override fun onVolumeChanged(volume: Float) {
            Log.d(TAG, "onVolumeChanged: $volume")
        }
    }

    override fun onDestroy() {
        simpleExoPlayer!!.release()
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Log.d(TAG, "onTouch: ")
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTap: clicked")
        val currentTime = simpleExoPlayer?.contentPosition
        if (currentTime != null){
            simpleExoPlayer?.seekTo(currentTime +5000)
        }
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTapEvent: ")
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return true
       
    }

    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
       return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {

    }
}