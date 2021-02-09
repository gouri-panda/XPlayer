package com.one4ll.xplayer

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import butterknife.BindView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.one4ll.xplayer.helpers.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity(), View.OnTouchListener, GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
    @BindView(R.id.main_activity_constraint_layout)
    var constraintLayout: ConstraintLayout? = null
    private lateinit var gestureDetector: GestureDetector
    private lateinit var videoUriPath: String
    private var screenWidth: Int? = null
    private var screenHeight: Int? = null
    private val audioManager by lazy { getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    private val brightNess by lazy { Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS) }

    @BindView(R.id.player_view)
    var playerView: PlayerView? = null
    private var simpleExoPlayer: SimpleExoPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideSystemUI(true)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        screenHeight = displayMetrics.heightPixels
        Log.d(TAG, "onCreate: width $screenWidth")
        Log.d(TAG, "onCreate: height $screenHeight")
        playerView = findViewById(R.id.player_view)
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        gestureDetector = GestureDetector(this, this)
        playerView?.player = simpleExoPlayer
        val intent = intent
        if (intent.action != null && intent.action == Intent.ACTION_VIEW) {

            Log.d(TAG, "onCreate: action view intent called")
            videoUriPath = intent.data.toString()
            Log.d(TAG, "onCreate: action view intent called $videoUriPath")

        } else {
            videoUriPath = intent.getStringExtra(VIDEO_PATH)
        }
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this, Util.getUserAgent(this@MainActivity, getString(R.string.app_name)))
        Log.d(TAG, "onCreate: video path $videoUriPath")
        lateinit var mediaSource: MediaSource
        val file = File(videoUriPath)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        val uri = Uri.parse(videoUriPath)
        val decode = BitmapFactory.decodeFile(videoUriPath,
                options)
        val width = decode?.width
        val height = decode?.height
        options.inJustDecodeBounds = false
        Log.d(TAG, "onCreate: width $width")
        Log.d(TAG, "onCreate: height $height")
        foo()
//        mediaSource =
//            ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.fromFile(File(videoUriPath)))
        mediaSource =
                ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoUriPath));
//        }
        simpleExoPlayer!!.prepare(mediaSource)

        simpleExoPlayer!!.playWhenReady = true
        simpleExoPlayer!!.addListener(eventListener)
        playerView?.setOnTouchListener(this)

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
            when (playbackState) {
                Player.STATE_READY -> {
                    goto_duration.visibility = View.INVISIBLE

                    Log.d(TAG, "onPlayerStateChanged: state ready")
                }
                Player.STATE_BUFFERING -> {
                    Log.d(TAG, "onPlayerStateChanged: buffering")
                }
                Player.STATE_ENDED -> {
                    playerView?.keepScreenOn = false
                    simpleExoPlayer?.playWhenReady = false
                    Log.d(TAG, "onPlayerStateChanged: state ended")
                }
            }
        }

        override fun onPlaybackSuppressionReasonChanged(playbackSuppressionReason: Int) {}
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            Log.d(TAG, "onIsPlayingChanged: isPlaying$isPlaying")
            if (!isPlaying) {
                playerView?.keepScreenOn = false;
            }
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


    override fun onDestroy() {
        simpleExoPlayer!!.release()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        playerView?.showController()
        return true
    }

    override fun onDoubleTap(event: MotionEvent?): Boolean {
        forwardAndBackWardVideoOnDoubleClick(event)
        return true
    }

    private fun forwardAndBackWardVideoOnDoubleClick(event: MotionEvent?) {

        Log.d(TAG, "onDoubleTap: clicked")
        var rawX = event?.rawX
        Log.d(TAG, "forwardAndBackWardVideoOnDoubleClick: rawx $rawX")
        if (rawX != null) {
            val middle = screenWidth!! / 2
            if (rawX < middle) {
                Log.d(TAG, "forwardAndBackWardVideoOnDoubleClick: left side")
                val currentPosition = simpleExoPlayer?.currentPosition
                if (currentPosition != null) {
                    simpleExoPlayer?.seekTo(currentPosition - SKIP_DURATION)
                }
            } else {
                Log.d(TAG, "forwardAndBackWardVideoOnDoubleClick: right side")
                val currentPosition = simpleExoPlayer?.currentPosition
                if (currentPosition != null) {
                    simpleExoPlayer?.seekTo(currentPosition + SKIP_DURATION)
                }

            }
        }
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
        if (playerView?.controllerAutoShow != null) {
            playerView?.controllerAutoShow = !playerView!!.controllerAutoShow

        }
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d(TAG, "onFling: velocity x $velocityX")
        Log.d(TAG, "onFling: velocity y $velocityY")
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.d(TAG, "onScroll: motion event 1$e1")
        Log.d(TAG, "onScroll: motion event 2 $e2")

        goto_duration.visibility = View.VISIBLE
        var currentPosition = simpleExoPlayer?.currentPosition ?: 0
        if (e2?.x!! - e1?.x!! >= 100) {
            //forward
            goto_duration.text = "Forward  +20.00s\n\t\t ${convertDuration(currentPosition)}s"
            simpleExoPlayer?.seekTo(currentPosition + 20000)

        } else if (e1.x - e2.x >= 100) {
            //backward
            goto_duration.text = "BackWard -20.00s\n\t\t ${convertDuration(currentPosition)}"
            simpleExoPlayer?.seekTo(currentPosition - 20000)
        } else {
            val rawX = e1.rawX
            val middle = screenWidth!! / 2
            if (rawX < middle) {
                //left brightness
                if (e1.y - e2.y >= 100) {
                    //increase brightness

                    increaseBrightNess()

                } else if (e2.y - e1.y >= 100) {
                    // decrease brightness
                    decreaseBrightNess()
                }
            } else {
                //right sound
                if (e1.y - e2.y > 100) {
                    //increase sound
                    increaseSound()
                } else if (e2.y - e1.y >= 100) {
                    //decrease sound
                    decreaseSound()
                }
                //change sound
            }
        }
        Log.d(TAG, "onScroll: distance x $distanceX")
        Log.d(TAG, "onScroll: distance y $distanceY")
        return true
    }

    private fun decreaseSound() {
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND)
    }

    private fun increaseSound() {
        Log.d(TAG, "increaseSound: iiii")
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
    }

    private fun decreaseBrightNess() {
        Log.d(TAG, "decreaseBrightNess: iiii")
    }

    private fun increaseBrightNess() {
        Log.d(TAG, "increaseBrightNess: iiii")
        Log.d(TAG, "increaseBrightNess: iiii brightness $brightNess")
        if (havePermission(this, Manifest.permission.WRITE_SETTINGS)) {

            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 23)
            val attributes = window.attributes
            attributes.screenBrightness = (23 / 255).toFloat()
            window.attributes = attributes
        } else {
//            askPermission(activity = this, permissions = *arrayOf(Manifest.permission.WRITE_SETTINGS),permissionId = 5)
        }
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer?.playWhenReady = false
    }

    fun foo() {
        if (!videoUriPath.contains("http") || !videoUriPath.contains("https")) {
            val mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(videoUriPath)
            val width = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)
            val height = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)
            Log.d(TAG, "foo: title ${mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)}")
            Log.d(TAG, "foo: ${mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)}")
            Log.d(TAG, "foo: width $width")
            Log.d(TAG, "foo: height $height")
        }

        val track = simpleExoPlayer?.currentTrackGroups
        for (i in 0 until track?.length!!) {
            Log.d(TAG, "foo: track ${track.get(i)}")
        }
    }

//    override fun onResume() {
//        super.onResume()
//        simpleExoPlayer?.playWhenReady = false
//    }
}
