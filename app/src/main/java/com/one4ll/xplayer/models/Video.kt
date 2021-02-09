package com.one4ll.xplayer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "video")
data class Video(
        @PrimaryKey(autoGenerate = true) var id: Int?,
        var title: String,
        var path: String,
        var duration: String
) {

}