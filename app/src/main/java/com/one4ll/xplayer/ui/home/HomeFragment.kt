package com.one4ll.xplayer.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.one4ll.xplayer.R
import com.one4ll.xplayer.RecylerViewAdapter
import com.one4ll.xplayer.helpers.getExternalContentImageUri
import com.one4ll.xplayer.helpers.getExternalContentVideoUri
import com.one4ll.xplayer.helpers.getInternalContentImageUri
import com.one4ll.xplayer.helpers.getInternalContentVideoUri
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
private val TAG = "homefragment"
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var root : View

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
         root = inflater.inflate(R.layout.fragment_home, container, false)
        val exUri = getExternalContentVideoUri(root.context)
        val inUri = getInternalContentVideoUri(root.context)
        exUri.addAll(inUri)
        Log.d(TAG, "onCreateView: exsize ${exUri.size}")
        val adapter = RecylerViewAdapter(exUri)
        root.video_list_recycler_view.adapter = adapter
        val lineaLayoutManager = LinearLayoutManager(root.context,LinearLayoutManager.VERTICAL,false)
        root.video_list_recycler_view.layoutManager = lineaLayoutManager

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}