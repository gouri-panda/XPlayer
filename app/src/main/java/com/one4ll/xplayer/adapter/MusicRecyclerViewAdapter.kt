package com.one4ll.xplayer.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.helpers.setMusicThumbNail
import kotlinx.coroutines.launch

class MusicRecyclerViewAdapter(var list: List<Media>, private val lifecycleCoroutineScope: LifecycleCoroutineScope) :
        RecyclerView.Adapter<MusicRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.video_list, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun loadVideo(list: List<Media>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: 10/07/20 fix when go to down to up
        holder.imageView.setImageBitmap(null)
        holder.title.text = list[position].name
        holder.duration.text = list[position].duration
        val path = list[position].path
        lifecycleCoroutineScope.launch {
            setMusicThumbNail(path, holder.imageView)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("video", path)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val title: TextView = itemView.findViewById(R.id.name)
        val duration: TextView = itemView.findViewById(R.id.duration)

    }
}