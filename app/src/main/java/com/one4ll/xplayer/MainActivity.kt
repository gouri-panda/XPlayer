package com.one4ll.xplayer

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.one4ll.xplayer.databinding.ActivityMainBinding
import com.one4ll.xplayer.helpers.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_exoplayer_control_views.view.*

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnTouchListener, GestureDetector.OnDoubleTapListener,
    GestureDetector.OnGestureListener, AudioManager.OnAudioFocusChangeListener {
    private lateinit var gestureDetector: GestureDetector
    private lateinit var videoUriPath: String
    private var screenWidth: Int? = null
    private var screenHeight: Int? = null
    private val audioManager by lazy { getSystemService(Context.AUDIO_SERVICE) as AudioManager }
    private val brightness by lazy {
        Settings.System.getInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS
        )
    }
    private lateinit var binding: ActivityMainBinding

    private var simpleExoPlayer: SimpleExoPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI(true)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        screenHeight = displayMetrics.heightPixels
        Log.d(TAG, "onCreate: screen width $screenWidth ,screen  height $screenHeight")
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        gestureDetector = GestureDetector(this, this)
        binding.root.player_view.player = simpleExoPlayer
        if (intent.action != null && intent.action == Intent.ACTION_VIEW) {
            Log.d(TAG, "onCreate: action view intent called")
            videoUriPath = intent.data.toString()
            Log.d(TAG, "onCreate: action view intent called $videoUriPath")
        } else {
            videoUriPath = intent.getStringExtra(VIDEO_PATH)
                ?: throw IllegalArgumentException(getString(R.string.videoPathShouldnotNull))
        }
        setVideoTitle()
        setViews()

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            this,
            Util.getUserAgent(this@MainActivity, getString(R.string.app_name))
        )
        Log.d(TAG, "onCreate: video path $videoUriPath")
        lateinit var mediaSource: MediaSource
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        val decode = BitmapFactory.decodeFile(videoUriPath, options)
        val width = decode?.width
        val height = decode?.height
        options.inJustDecodeBounds = false
        Log.d(
            TAG,
            "onCreate: video uri decode file width $width video uri decode file height $height"
        )
        mediaSource =
            ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUriPath))
        simpleExoPlayer!!.prepare(mediaSource)

        simpleExoPlayer!!.playWhenReady = true
        simpleExoPlayer!!.addListener(eventListener)

    }

    private fun setViews() {
        binding.root.player_view?.setOnTouchListener(this)
        binding.root.player_view.cross_image.setOnClickListener { finish() }
    }

    private fun setVideoTitle() {
        val title = videoUriPath.substringAfterLast("/").substringBefore(".", "")
        binding.playerView.video_title.text = title
    }

    private val eventListener: Player.EventListener = object : Player.EventListener {
        override fun onTimelineChanged(timeline: Timeline, reason: Int) {
            Log.d(TAG, "onTimelineChanged: timeline $timeline reason $reason")
        }

        override fun onTimelineChanged(timeline: Timeline, manifest: Any?, reason: Int) {
            Log.d(TAG, "onTimelineChanged: timeline $timeline , manifest $manifest, reason $reason")
        }


        override fun onTracksChanged(
            trackGroups: TrackGroupArray,
            trackSelections: TrackSelectionArray
        ) {
        }

        override fun onLoadingChanged(isLoading: Boolean) {
        }


        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            when (playbackState) {
                Player.STATE_READY -> {
                    binding.root.goto_duration.visibility = View.INVISIBLE
                    audioManager.requestAudioFocus(
                        this@MainActivity,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN
                    )
                    Log.d(TAG, "onPlayerStateChanged: state ready")
                }
                Player.STATE_BUFFERING -> {
                    Log.d(TAG, "onPlayerStateChanged: buffering")
                }
                Player.STATE_ENDED -> {
                    binding.root.player_view?.keepScreenOn = false
                    simpleExoPlayer?.playWhenReady = false
                    Log.d(TAG, "onPlayerStateChanged: state ended")
                }
                Player.STATE_IDLE -> {
                    //todo
                }
            }
        }

        override fun onPlaybackSuppressionReasonChanged(playbackSuppressionReason: Int) {}
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            Log.d(TAG, "onIsPlayingChanged: isPlaying$isPlaying")
            if (!isPlaying) {
                binding.root.player_view?.keepScreenOn = false
            }
        }

        override fun onRepeatModeChanged(repeatMode: Int) {}
        override fun onPlayerError(error: ExoPlaybackException) {
            Log.d(TAG, "onPlayerError:" + error.message).also { error.printStackTrace() }
            Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_LONG).show()
        }

        override fun onPositionDiscontinuity(reason: Int) {}
        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {}
    }

    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK -> {
                simpleExoPlayer?.playWhenReady = true
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                simpleExoPlayer?.playWhenReady = false
            }
            AudioManager.AUDIOFOCUS_LOSS -> {
                simpleExoPlayer?.playWhenReady = false
            }
        }
    }


    override fun onDestroy() {
        simpleExoPlayer!!.release()
        super.onDestroy()
    }


    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        view?.performClick()
        gestureDetector.onTouchEvent(event)
        binding.root.player_view?.showController()
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
        if (binding.root.player_view?.controllerAutoShow != null) {
            binding.root.player_view?.controllerAutoShow =
                !binding.root.player_view!!.controllerAutoShow
        }
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
        binding.root.goto_duration.visibility = View.VISIBLE
        val currentPosition = simpleExoPlayer?.currentPosition ?: 0
        if (motionEvent2?.x!! - motionEvent1?.x!! >= 100) {
            //forward
            binding.root.goto_duration.text =
                "Forward  +20.00s\n\t\t ${convertDuration(currentPosition)}s"
            simpleExoPlayer?.seekTo(currentPosition + 20000)

        } else if (motionEvent1.x - motionEvent2.x >= 100) {
            //backward
            binding.root.goto_duration.text =
                "BackWard -20.00s\n\t\t ${convertDuration(currentPosition)}"
            simpleExoPlayer?.seekTo(currentPosition - 20000)
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
        simpleExoPlayer?.playWhenReady = false
    }
}
