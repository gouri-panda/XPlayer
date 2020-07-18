package com.one4ll.xplayer.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.FullImageActivity
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.helpers.IMAGE_PATH
import com.one4ll.xplayer.helpers.setImageThumbNail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

class ImageRecylerViewAdapter(var list: List<Media>,var activity: Activity) :
        RecyclerView.Adapter<ImageRecylerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.video_list, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setVideoList(list: ArrayList<Media>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: 10/07/20 fix when go to down to up
        holder.imageView.setImageBitmap(null)
        holder.title.text = list[position].name
        val path = list[position].path
        CoroutineScope(Default).launch {
            setImageThumbNail(path, holder.imageView)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FullImageActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(activity,holder.imageView,holder.imageView.transitionName).toBundle()
            intent.putExtra(IMAGE_PATH, path)
            holder.itemView.context.startActivity(intent,bundle)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val title = itemView.findViewById<TextView>(R.id.name)
        val duration = itemView.findViewById<TextView>(R.id.duration).apply { visibility = View.GONE }

    }
}