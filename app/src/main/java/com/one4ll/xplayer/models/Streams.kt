package com.one4ll.xplayer.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "streams")
class Streams(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        var path: String,
        var time: Long
) {
    @Ignore
    constructor(path: String, time: Long) : this(null, path, time)

}