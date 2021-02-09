package com.one4ll.xplayer.ui.stream

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
private const val TAG = "streamFragment"

class StreamFragment : Fragment() {
    private lateinit var rootView: View
    private val db by lazy { MediaDatabase.getInstance(rootView.context) }
    private lateinit var adapter: StreamsRecyclerViewAdapter
    private val viewModel: StreamViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_stream, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.streamsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                adapter.setNotes(it)
            }
        })
        lifecycleScope.launch {
            setRecycleView()
        }
        rootView.url_send.setOnClickListener {
            val url = rootView.editTextUrl?.text
            if (url != null) {
                val intent = Intent(rootView.context, MainActivity::class.java)
                intent.putExtra(VIDEO_PATH, url.toString())
                lifecycleScope.launch {
                    insertStreamsIntoDatabase(db, Streams(url.toString(), System.currentTimeMillis()))
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
                lifecycleScope.launch {
                    withContext(IO) {
                        db.streamsDao().removeById(adapter.getStreamAtPosition(viewHolder.adapterPosition)?.id!!)
                        withContext(Main) {
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }).attachToRecyclerView(rootView.streams_recycler_view)
    }

    private suspend fun setRecycleView() {
        withContext(IO) {
            adapter = StreamsRecyclerViewAdapter()
            withContext(Main) {
                rootView.streams_recycler_view.apply {
                    val animation = AnimationUtils.loadAnimation(rootView.context, R.anim.recycler_view_from_bottom_to_top)
                    this.animation = animation
                    layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.VERTICAL, false)
                    adapter = this@StreamFragment.adapter
                }

            }
        }
    }
}

private suspend fun insertStreamsIntoDatabase(database: MediaDatabase, stream: Streams) = withContext(IO) {
    database.streamsDao().insert(stream)
}

