package com.one4ll.xplayer.custom

import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView

private const val TAG = "XPlayer"

class CustomPlayerEventListener(
    private val goToTextView: TextView,
    private val playerView: PlayerView,
    private val simpleExoPlayer: SimpleExoPlayer
) : Player.EventListener {
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
                goToTextView.visibility = View.INVISIBLE
                // TODO: is the requestAudioFocus necessary here??
//                    audioManager.requestAudioFocus(
//                        this@MainActivity,
//                        AudioManager.STREAM_MUSIC,
//                        AudioManager.AUDIOFOCUS_GAIN
//                    )
                Log.d(TAG, "onPlayerStateChanged: state ready")
            }
            Player.STATE_BUFFERING -> {
                Log.d(TAG, "onPlayerStateChanged: buffering")
            }
            Player.STATE_ENDED -> {
                playerView.keepScreenOn = false
                simpleExoPlayer.playWhenReady = false
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
            playerView.keepScreenOn = false
        }
    }

    override fun onRepeatModeChanged(repeatMode: Int) {}
    override fun onPlayerError(error: ExoPlaybackException) {
        Log.d(TAG, "onPlayerError:" + error.message).also { error.printStackTrace() }
        Toast.makeText(playerView.context, error.message, Toast.LENGTH_LONG).show()
    }

}
