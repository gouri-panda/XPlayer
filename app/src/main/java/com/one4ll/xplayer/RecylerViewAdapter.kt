package com.one4ll.xplayer

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class RecylerViewAdapter(var list: ArrayList<Video>) :
    RecyclerView.Adapter<RecylerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.video_list,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun loadVideo(list: ArrayList<Video>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text= list[position].name
        holder.duration.text = list[position].duration
        holder.videoView.setBackgroundDrawable(BitmapDrawable(list[position].thumbnail))
        holder.videoView.seekTo(1)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,MainActivity::class.java)
            intent.putExtra("video",list[position].path)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val videoView = itemView.findViewById<VideoView>(R.id.videoView)
        val title = itemView.findViewById<TextView>(R.id.name)
        val duration = itemView.findViewById<TextView>(R.id.duration)

 }
}