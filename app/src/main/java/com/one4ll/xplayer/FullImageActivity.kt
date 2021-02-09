package com.one4ll.xplayer

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.one4ll.xplayer.helpers.IMAGE_PATH
import kotlinx.android.synthetic.main.activity_full_image.*

private const val TAG = "FullImageActivity"

class FullImageActivity : AppCompatActivity(), View.OnTouchListener, GestureDetector.OnGestureListener, View.OnDragListener, GestureDetector.OnDoubleTapListener {
    private lateinit var gestureDetector: GestureDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        val path = intent.getStringExtra(IMAGE_PATH)
        Log.d(TAG, "onCreate: image path $path")
        full_image_view.setImageURI(Uri.parse(path))
        gestureDetector = GestureDetector(this, this)
        full_image_view.setOnDragListener(this)
        full_image_view.setOnTouchListener(this)
    }


    //On touch listener

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "onTouch: action down")
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "onTouch: action up")
            }
            MotionEvent.ACTION_OUTSIDE -> {
                Log.d(TAG, "onTouch: action outside")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "onTouch: x ${event.x} Y is ${event.y}")
            }

        }
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
        val builder = View.DragShadowBuilder(full_image_view)
        full_image_view.startDrag(null, builder, null, 0)
        builder.view.setOnDragListener(this)
    }


    //This is from View.OnDragListener
    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        Log.d(TAG, "onDrag: ")
        when (event?.action) {
            DragEvent.ACTION_DRAG_ENDED -> {
                Log.d(TAG, "onDrag: action drag ended")
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                Log.d(TAG, "onDrag: action drag entered")
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                Log.d(TAG, "onDrag: action drag exited")
            }
            DragEvent.ACTION_DRAG_LOCATION -> {
                Log.d(TAG, "onDrag: action drag location")
            }
            DragEvent.ACTION_DRAG_STARTED -> {
                Log.d(TAG, "onDrag: action drage started")
            }
            DragEvent.ACTION_DROP -> {
                Log.d(TAG, "onDrag: action drop")
            }
        }
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.d(TAG, "onDoubleTap: ")
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
