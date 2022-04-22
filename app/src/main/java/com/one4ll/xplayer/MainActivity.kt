package com.one4ll.xplayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.one4ll.xplayer.custom.CustomPlayerEventListener
import com.one4ll.xplayer.helpers.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnTouchListener, GestureDetector.OnDoubleTapListener,
    GestureDetector.OnGestureListener, AudioManager.OnAudioFocusChangeListener {
    private lateinit var gestureDetector: GestureDetector
    private lateinit var videoUriPath: String
    private lateinit var playerView: PlayerView
    private lateinit var goToTextView: TextView
    private lateinit var crossImageView: ImageView
    private lateinit var videoTitle: TextView
    private var screenWidth: Int? = null
    private var screenHeight: Int? = null
    private val brightness by lazy {
        Settings.System.getInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS
        )
    }

    @Inject
    lateinit var audioManager: AudioManager

    @Inject
    lateinit var simpleExoPlayer: SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playerView = findViewById(R.id.player_view)
        goToTextView = findViewById(R.id.goto_duration)
        crossImageView = findViewById(R.id.cross_image)
        videoTitle = findViewById(R.id.video_title)
        hideSystemUI(true)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        screenHeight = displayMetrics.heightPixels
        Log.d(TAG, "onCreate: screen width $screenWidth ,screen  height $screenHeight")
        gestureDetector = GestureDetector(this, this)
        playerView.player = simpleExoPlayer
        if (intent.action != null && intent.action == Intent.ACTION_VIEW) {
            Log.d(TAG, "onCreate: action view intent called")
            videoUriPath = intent.data.toString()
            Log.d(TAG, "onCreate: action view intent called $videoUriPath")
        } else {
            videoUriPath = intent.getStringExtra(VIDEO_PATH)
                ?: throw IllegalArgumentException(getString(R.string.videoPathShouldnotNull))
        }
        setVideoTitle()
        setViewListener()

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this@MainActivity, getString(R.string.app_name))
        )
        Log.d(TAG, "onCreate: Video Path $videoUriPath")
        lateinit var mediaSource: MediaSource
        mediaSource =
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUriPath))
        simpleExoPlayer.prepare(mediaSource)

        simpleExoPlayer.playWhenReady = true
        val eventListener = CustomPlayerEventListener(
            goToTextView = goToTextView,
            playerView = playerView,
            simpleExoPlayer = simpleExoPlayer
        )
        simpleExoPlayer.addListener(eventListener)

    }

    private fun setViewListener() {
        playerView.setOnTouchListener(this)
        crossImageView.setOnClickListener { finish() }
    }

    private fun setVideoTitle() {
        videoTitle.text = videoUriPath.getTitleFromVideoPath()
    }


    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK -> {
                simpleExoPlayer.playWhenReady = true
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                simpleExoPlayer.playWhenReady = false
            }
            AudioManager.AUDIOFOCUS_LOSS -> {
                simpleExoPlayer.playWhenReady = false
            }
        }
    }


    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        view?.performClick()
        gestureDetector.onTouchEvent(event)
        playerView.showController()
        return true
    }

    override fun onDoubleTap(event: MotionEvent?): Boolean {
        forwardAndBackWardVideoOnDoubleClick(event)
        return true
    }

    private fun forwardAndBackWardVideoOnDoubleClick(event: MotionEvent?) {
        Log.d(TAG, "onDoubleTap: clicked")
        val rawX = event?.rawX
        Log.d(TAG, "forwardAndBackWardVideoOnDoubleClick: raw x $rawX")
        if (rawX != null) {
            val middle = screenWidth!! / 2
            if (rawX < middle) {
                Log.d(TAG, "forwardAndBackWardVideoOnDoubleClick: Left side")
                val currentPosition = simpleExoPlayer.currentPosition
                simpleExoPlayer.seekTo(currentPosition - SKIP_DURATION)
            } else {
                Log.d(TAG, "forwardAndBackWardVideoOnDoubleClick: Right side")
                val currentPosition = simpleExoPlayer.currentPosition
                simpleExoPlayer.seekTo(currentPosition + SKIP_DURATION)

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
        playerView.controllerAutoShow =
            !playerView.controllerAutoShow
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        Log.d(TAG, "onFling: velocity x $velocityX velocity y $velocityY")
        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onScroll(
        motionEvent1: MotionEvent?,
        motionEvent2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        Log.d(TAG, "onScroll: motion event 1$motionEvent1")
        Log.d(TAG, "onScroll: motion event 2 $motionEvent2")
        goToTextView.visibility = View.VISIBLE
        val currentPosition = simpleExoPlayer?.currentPosition ?: 0
        if (motionEvent2?.x!! - motionEvent1?.x!! >= 100) {
            //forward
            goToTextView.text =
                "Forward  +20.00s\n\t\t ${convertDuration(currentPosition)}s"
            simpleExoPlayer.seekTo(currentPosition + 20000)

        } else if (motionEvent1.x - motionEvent2.x >= 100) {
            //backward
            goToTextView.text =
                "BackWard -20.00s\n\t\t ${convertDuration(currentPosition)}"
            simpleExoPlayer.seekTo(currentPosition - 20000)
        } else {
            val rawX = motionEvent1.rawX
            val middle = screenWidth!! / 2
            if (rawX < middle) {
                //left brightness
                if (motionEvent1.y - motionEvent2.y >= 100) {
                    //increase brightness

                    increaseBrightness()

                } else if (motionEvent2.y - motionEvent1.y >= 100) {
                    // decrease brightness
                    decreaseBrightness()
                }
            } else {
                //right sound
                if (motionEvent1.y - motionEvent2.y > 100) {
                    //increase sound
                    increaseSound()
                } else if (motionEvent2.y - motionEvent1.y >= 100) {
                    //decrease sound
                    decreaseSound()
                }
                //change sound
            }
        }
        Log.d(TAG, "onScroll: distance x $distanceX and distant y $distanceY")
        return true
    }

    private fun decreaseSound() {
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND)
    }

    private fun increaseSound() {
        Log.d(TAG, "increaseSound:")
        audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND)
    }

    // TODO: Add brightness control
    private fun decreaseBrightness() {
        Log.d(TAG, "decreaseBrightness:")
    }

    private fun increaseBrightness() {
        Log.d(TAG, "increaseBrightness: brightness $brightness")
        if (havePermission(this, Manifest.permission.WRITE_SETTINGS)) {

            Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 23)
            val attributes = window.attributes
            attributes.screenBrightness = (23 / 255).toFloat()
            window.attributes = attributes
        }
    }

    override fun onLongPress(e: MotionEvent?) {

    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.playWhenReady = false
    }
}
