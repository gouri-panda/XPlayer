package com.one4ll.xplayer.ui.stream

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.StreamsRecyclerViewAdapter
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.helpers.VIDEO_PATH
import com.one4ll.xplayer.models.Streams
import kotlinx.android.synthetic.main.fragment_stream.*
import kotlinx.android.synthetic.main.fragment_stream.view.*
import kotlinx.android.synthetic.main.fragment_stream.view.streams_recycler_view
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.log

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StreamFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private val TAG = "streamFragment"

class StreamFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rootView: View
    private val db by lazy { MediaDatabase.getInstance(rootView.context) }
    private lateinit var adapter: StreamsRecyclerViewAdapter
    private lateinit var viewModel: StreamViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_stream, container, false)
        viewModel = ViewModelProviders.of(this).get(StreamViewModel::class.java)
        viewModel.streamsList.observe(viewLifecycleOwner, Observer { t ->
            if (t != null){
                adapter.setNotes(t)
            }
        })
        CoroutineScope(IO).launch {
            setRecycleView()
        }
        rootView.url_send.setOnClickListener {
            val url = rootView.editTextUrl?.text
            if (url != null) {
                val intent = Intent(rootView.context, MainActivity::class.java)
                intent.putExtra(VIDEO_PATH, url.toString())
                CoroutineScope(IO).launch {
                    insertStreamsIntoDatabase(db, Streams(url.toString(), System.currentTimeMillis()))
                    getStreamsFromDatabase(db)
                }
                Log.d(TAG, "onCreateView: stream url path $url")
                rootView.context.startActivity(intent)

            }
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.ANIMATION_TYPE_DRAG, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                Log.d(TAG, "onMove: moved ")
                Log.d(TAG, "onMove: layout positon ${viewHolder.layoutPosition}")
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d(TAG, "onSwiped: direction $direction")
                CoroutineScope(IO).launch {
                    db.streamsDao().removeById(adapter.getStreamAtPosition(viewHolder.adapterPosition)?.id!!)
                    withContext(Main){
                        adapter.notifyDataSetChanged()
                    }

                }
            }
        }).attachToRecyclerView(rootView.streams_recycler_view)
        return rootView
    }

    private suspend fun setRecycleView() {
        withContext(IO) {
            async {
                adapter = StreamsRecyclerViewAdapter()
            }.await()
            async(Main) {
                rootView.streams_recycler_view.apply {
                    val animation = AnimationUtils.loadAnimation(rootView.context,R.anim.recycler_view_from_bottom_to_top)
                    this.animation = animation
                    layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.VERTICAL, false)
                    adapter = this@StreamFragment.adapter
                }

            }
        }
    }
}

private suspend fun insertStreamsIntoDatabase(database: MediaDatabase, stream: Streams) {
    withContext(IO) {
        database.streamsDao().insert(stream)
    }
}

private suspend fun getStreamsFromDatabase(database: MediaDatabase) {
    withContext(IO) {
        var streamsList = database.streamsDao().getAllByTime()
        streamsList.value?.forEach {
            Log.d(TAG, "getStreamsFromDatabase: id = ${it.id}")
            Log.d(TAG, "getStreamsFromDatabase: path = ${it.path}")
            Log.d(TAG, "getStreamsFromDatabase: time = ${it.time}")
        }

    }
}