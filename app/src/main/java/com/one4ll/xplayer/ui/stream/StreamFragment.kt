package com.one4ll.xplayer.ui.stream

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.R
import com.one4ll.xplayer.database.MediaDatabase
import com.one4ll.xplayer.helpers.VIDEO_PATH
import com.one4ll.xplayer.models.Streams
import kotlinx.android.synthetic.main.fragment_stream.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
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
private val TAG ="streamFragment"
class StreamFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rootView: View
    private val db  by lazy { MediaDatabase.getInstance(rootView.context) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_stream, container, false)
        rootView.url_send.setOnClickListener {
            val url = rootView.editTextUrl?.text
            if (url != null) {
                val intent = Intent(rootView.context, MainActivity::class.java)
                intent.putExtra(VIDEO_PATH, url.toString())
                CoroutineScope(IO).launch {
                    insertStreamsIntoDatabase(db, Streams(url.toString(),System.currentTimeMillis()))

                    getStreamsFromDatabase(db)
                }
                Log.d(TAG, "onCreateView: stream url path $url")
                rootView.context.startActivity(intent)

            }
        }
        return rootView
    }
}
private suspend fun insertStreamsIntoDatabase(database: MediaDatabase,stream : Streams){
    withContext(IO){
        database.streamsDao().insert(stream)
    }
}
private suspend fun getStreamsFromDatabase(database: MediaDatabase){
    withContext(IO){
        var streamsList = database.streamsDao().getAllByTime()
        streamsList.forEach {
            Log.d(TAG, "getStreamsFromDatabase: id = ${it.id}")
            Log.d(TAG, "getStreamsFromDatabase: path = ${it.path}")
            Log.d(TAG, "getStreamsFromDatabase: time = ${it.time}")
        }

    }
}