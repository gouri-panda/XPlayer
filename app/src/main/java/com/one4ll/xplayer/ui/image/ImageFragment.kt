package com.one4ll.xplayer.ui.image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import com.one4ll.xplayer.models.Image
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
private val TAG = "galleryfragment"
class GalleryFragment : Fragment() {

    private lateinit var imageViewModel: ImageViewModel
    private lateinit var root: View
    private lateinit var exImageUri: ArrayList<Media>
    private lateinit var inImageUri: List<Media>
    private  var dataBase: MediaDatabase? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        imageViewModel =
                ViewModelProviders.of(this).get(ImageViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_gallery, container, false)
        if (dataBase == null) {
            dataBase = MediaDatabase.getInstance(root.context)
        }
        exImageUri = getExternalContentImageUri(root.context)
        inImageUri = getInternalContentImageUri(root.context)
        exImageUri.addAll(inImageUri)
        val adapter = ImageRecylerViewAdapter(exImageUri)
        val linearLayoutManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
        root.image_list_recycler_view.layoutManager = linearLayoutManager
        root.image_list_recycler_view.adapter = adapter
        val imageList = ArrayList<Image>()
        var count = 0
        exImageUri.forEach {
            imageList.add(Image( it.name, it.path, it.duration))
        }
        runBlocking {
            withContext(IO) {
                if (dataBase != null){

                dataBase?.imageDao()!!.insertImages(imageList)
                dataBase?.imageDao()!!.getAll().forEach {
                    Log.d(TAG, "onCreateView: image Name ${it.title}")
                    Log.d(TAG, "onCreateView: image path ${it.path}")
                }
                }
            }
        }






        return root
    }
}