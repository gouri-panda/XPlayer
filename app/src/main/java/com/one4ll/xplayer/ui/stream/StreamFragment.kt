package com.one4ll.xplayer.ui.stream

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.R
import com.one4ll.xplayer.adapter.StreamsRecyclerViewAdapter
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.helpers.VIDEO_PATH
import com.one4ll.xplayer.helpers.baseViewModel
import com.one4ll.xplayer.models.Streams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.*


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
private const val TAG = "streamFragment"

@ExperimentalCoroutinesApi
class StreamFragment : Fragment() {
    private val adapter: StreamsRecyclerViewAdapter by lazy { StreamsRecyclerViewAdapter() }
    private lateinit var urlSendButton: ImageView
    private lateinit var urlEditTextButton: EditText
    private lateinit var streamsRecyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stream, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setRecycleView()
        urlSendButton = view.findViewById(R.id.url_send)
        urlEditTextButton = view.findViewById(R.id.editTextUrl)
        streamsRecyclerView = view.findViewById(R.id.streams_recycler_view)
        lifecycleScope.launch {
            baseViewModel.streamsList.collect {
                adapter.setNotes(it)
            }
        }
        urlSendButton.setOnClickListener {
            val url = urlEditTextButton.text
            if (url != null) {
                val intent = Intent(view.context, MainActivity::class.java)
                intent.putExtra(VIDEO_PATH, url.toString())
                lifecycleScope.launch {
                    insertStreamsIntoDatabase(baseViewModel.db, Streams(url.toString(), System.currentTimeMillis()))
                }
                Log.d(TAG, "onCreateView: stream url path $url")
                startActivity(intent)
            }
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.ANIMATION_TYPE_DRAG,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                Log.d(TAG, "onMove: moved ")
                Log.d(TAG, "onMove: layout position ${viewHolder.layoutPosition}")
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d(TAG, "onSwiped: direction $direction")
                lifecycleScope.launch {
                    baseViewModel.db.streamsDao().removeById(adapter.getStreamAtPosition(viewHolder.adapterPosition).id!!)
                    adapter.notifyDataSetChanged()
                }
            }
        }).attachToRecyclerView(streamsRecyclerView)
    }

    private fun setRecycleView() {
        streamsRecyclerView.apply {
            val animation = AnimationUtils.loadAnimation(rootView.context, R.anim.recycler_view_from_bottom_to_top)
            this.animation = animation
            layoutManager = LinearLayoutManager(rootView.context, LinearLayoutManager.VERTICAL, false)
            adapter = this@StreamFragment.adapter
        }

    }

    private suspend fun insertStreamsIntoDatabase(database: MediaDatabase, stream: Streams) {
        database.streamsDao().insert(stream)
    }
}



