package com.one4ll.xplayer

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.one4ll.xplayer.databinding.ActivityFullImageBinding
import com.one4ll.xplayer.helpers.IMAGE_PATH
import kotlin.math.max
import kotlin.math.min

private const val TAG = "FullImageActivity"

class FullImageActivity : AppCompatActivity(), View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private lateinit var binding: ActivityFullImageBinding
    private lateinit var gestureDetector: GestureDetector
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleEffect: Float = 1.0f
    private var isDoubleTaped = false
    private lateinit var fullImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val path = intent.getStringExtra(IMAGE_PATH)
        Log.d(TAG, "onCreate: image path $path")
        fullImageView = findViewById(R.id.full_image_view)
        fullImageView.setImageURI(Uri.parse(path))
        scaleGestureDetector = ScaleGestureDetector(this, ScaleGestureListener(scaleEffect, fullImageView))
        gestureDetector = GestureDetector(this, this)
        fullImageView.setOnTouchListener(this)
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        gestureDetector.onTouchEvent(event)
        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    //On Gesture detect listener
    override fun onShowPress(e: MotionEvent?) {
        Log.d(TAG, "onShowPress: ")
        when (e?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "onShowPress: action down")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "onShowPress: action up")
            }
        }

    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.d(TAG, "onSingleTapUp: on single tap")
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDown: ")
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.d(TAG, "onFling: ")
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.d(TAG, "onScroll: ")
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.d(TAG, "onLongPress: ")
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTap: ")
        isDoubleTaped = !isDoubleTaped
        if (!isDoubleTaped) {
            scalesView(fullImageView, 4 * scaleEffect)
        } else {
            scalesView(fullImageView, 1.0f)
        }
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTapEvent: ")
        return true
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        Log.d(TAG, "onSingleTapConfirmed: ")
        return true
    }

}

private fun scalesView(imageView: View, scaleFactor: Float) {
    //Make sure if we don't scale too small or too large
    val mScaleFactor = max(0.1f, min(scaleFactor, 10.00f))
    imageView.animate().scaleX(mScaleFactor).scaleY(mScaleFactor).setDuration(300).start()
}

class ScaleGestureListener(private var scaleFactor: Float, var view: View) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        Log.d(TAG, "onScale: called")
        detector?.let {
            scaleFactor *= 2 * detector.scaleFactor
            scalesView(view, scaleFactor)
        }
        return true
    }
}