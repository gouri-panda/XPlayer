package com.one4ll.xplayer.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log.d
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.RecyclerView
import com.one4ll.xplayer.ActionModeCallback
import com.one4ll.xplayer.MainActivity
import com.one4ll.xplayer.Media
import com.one4ll.xplayer.R
import com.one4ll.xplayer.helpers.IS_GRID_LAYOUT
import com.one4ll.xplayer.helpers.SHARED_PREF_SETTINGS
import com.one4ll.xplayer.helpers.VIDEO_PATH
import com.one4ll.xplayer.helpers.setVideoThumbNail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.launch

private const val TAG = "VideoRecylerViewAdapter"

class VideoRecylerViewAdapter(val activity: Activity, var list: List<Media>) :
        RecyclerView.Adapter<VideoRecylerViewAdapter.ViewHolder>() {
    init {

    }

    protected lateinit var actionModeCallback: ActionModeCallback
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
            activity.applicationContext.setVideoThumbNail(path, holder.imageView)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MainActivity::class.java)
            intent.putExtra(VIDEO_PATH, path)
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            d(TAG, "onBindViewHolder: item clicked")
            actionModeCallback = object : ActionModeCallback() {
                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    d(TAG, "onActionItemClicked: ")
                    list[position].isSelectd = !list[position].isSelectd
                    val itemViewColor = if (list[position].isSelectd) Color.CYAN else Color.BLACK
                    holder.itemView.setBackgroundColor(itemViewColor)
                    return true
                }

                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    d(TAG, "onCreateActionMode: ")

                    activity.menuInflater.inflate(R.menu.test_item_select_listener, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    d(TAG, "onPrepareActionMode: ")
                    return true
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    d(TAG, "onDestroyActionMode: ")
                }

            }

            return@setOnLongClickListener true
        }
    }

    fun get(onclick: Onclick) {
        onclick.onClick("2")
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val title = itemView.findViewById<TextView>(R.id.name)
        val duration = itemView.findViewById<TextView>(R.id.duration)
    }


}

interface Onclick {
    fun onClick(time: String)
}
