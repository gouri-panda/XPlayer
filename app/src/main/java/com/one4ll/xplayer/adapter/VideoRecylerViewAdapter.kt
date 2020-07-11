package com.one4ll.xplayer.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.helpers.IS_GRID_LAYOUT
import com.one4ll.xplayer.helpers.SHARED_PREF_SETTINGS
import com.one4ll.xplayer.helpers.VIDEO_PATH
import com.one4ll.xplayer.helpers.setVideoThumbNail
import io.reactivex.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class VideoRecylerViewAdapter(var list: List<Media>) :
        RecyclerView.Adapter<VideoRecylerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View?
        val layoutInflater = LayoutInflater.from(parent.context)
        val sharedPreferences = parent.context.getSharedPreferences(SHARED_PREF_SETTINGS, Context.MODE_PRIVATE)
        val isGrid = sharedPreferences.getBoolean(IS_GRID_LAYOUT, false)
        view = if (isGrid) {
            layoutInflater.inflate(R.layout.file_list_in_grid, parent, false)
        } else {
            layoutInflater.inflate(R.layout.video_list, parent, false)
        }
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun loadVideo(list: ArrayList<Media>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: 10/07/20 fix when go to down to up
        holder.imageView.setImageBitmap(null)
        holder.title.text = list[position].name
        holder.duration.text = list[position].duration
        val path = list[position].path
        CoroutineScope(Default).launch {
            setVideoThumbNail(path, holder.imageView)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra(VIDEO_PATH, path)
            holder.itemView.context.startActivity(intent)
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val title = itemView.findViewById<TextView>(R.id.name)
        val duration = itemView.findViewById<TextView>(R.id.duration)
    }


}
