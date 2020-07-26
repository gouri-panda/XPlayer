package com.one4ll.xplayer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.security.cert.CertPath

@Entity(tableName = "favorite")
class Favorite(
        @PrimaryKey(autoGenerate = true) var id : Long?,
        var path: String,
        var time : Long

) {
    @Ignore constructor(path: String,time: Long) : this(null,path,time)
}