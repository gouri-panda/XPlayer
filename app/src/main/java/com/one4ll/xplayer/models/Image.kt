package com.one4ll.xplayer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
class Image(
    @PrimaryKey(autoGenerate = true)
    var _id : Int,
    var title : String,
    var path : String,
    var duration: String

) {

}