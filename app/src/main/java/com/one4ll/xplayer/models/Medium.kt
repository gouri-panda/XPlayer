package com.one4ll.xplayer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class Medium(
        @PrimaryKey(autoGenerate = true) var id : Int? ,
        var title : String,
        var path : String,
        var duration : String
) {

}