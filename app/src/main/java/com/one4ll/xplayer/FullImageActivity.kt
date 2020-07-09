package com.one4ll.xplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import com.one4ll.xplayer.helpers.IMAGE_PATH
import kotlinx.android.synthetic.main.activity_full_image.*
import java.io.File

private const val TAG = "FullImageActivity"
class FullImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        val path = intent.getStringExtra(IMAGE_PATH)
        Log.d(TAG, "onCreate: image path $path")
        full_image_view.setImageURI(Uri.parse(path))
    }
}
class FullImageAdapter : PagerAdapter(){
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return true
    }

    override fun getCount(): Int {
        return 2
    }
}