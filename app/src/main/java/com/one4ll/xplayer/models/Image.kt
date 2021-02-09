package com.one4ll.xplayer.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "image")
class Image(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        var title: String,
        var path: String,
        var duration: String

) {
    @Ignore
    constructor(title: String, path: String, duration: String) : this(null, title, path, duration)


}