package com.one4ll.xplayer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class VideoRecylerViewAdapter(var list: List<Media>) :
        RecyclerView.Adapter<VideoRecylerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.video_list, parent, false)
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
        holder.title.text = list[position].name
        holder.duration.text = list[position].duration

        setVideoThumbNail(list[position].path, holder.imageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra("video", list[position].path)


            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val title = itemView.findViewById<TextView>(R.id.name)
        val duration = itemView.findViewById<TextView>(R.id.duration)
    }

    private fun setVideoThumbNail(fiePath: String, imageView: ImageView) = CoroutineScope(Default).launch {
        var bitMap: Bitmap? = null
        async {
            bitMap = ThumbnailUtils.createVideoThumbnail(fiePath, MediaStore.Images.Thumbnails.MINI_KIND)
        }.await()
        withContext(Main) {
            imageView.setImageBitmap(bitMap)
        }
    }
}