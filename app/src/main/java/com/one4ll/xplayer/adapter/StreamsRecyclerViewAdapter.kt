package com.one4ll.xplayer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.R
import com.one4ll.xplayer.helpers.VIDEO_PATH
import com.one4ll.xplayer.models.Streams
import java.util.*


class StreamsRecyclerViewAdapter() : RecyclerView.Adapter<StreamsRecyclerViewAdapter.ViewHolder>() {
    private var streamList: List<Streams> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.streams_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return streamList.size
    }

    fun setNotes(listStreams: List<Streams>) {
        this.streamList = listStreams
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = streamList.get(position).path
        val date = Date(streamList[position].time)
        holder.date.text = date.toString()
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MainActivity::class.java)
            intent.putExtra(VIDEO_PATH, streamList[position].path)
            it.context.startActivity(intent)
        }
    }

    fun getStreamAtPosition(position: Int): Streams? {
        return streamList[position]
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var name: TextView = itemview.findViewById(R.id.stream_name)
        var date: TextView = itemview.findViewById(R.id.stream_date)

    }
}