package com.one4ll.xplayer.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.one4ll.xplayer.models.Video

@Dao
interface VideoDao {
    @Insert
    fun insert(video: Video)
    @Insert
    fun insertAll(video : List<Video>)
    @Query("SELECT * FROM video")
    fun getAll() : List<Video>
    @Query("DELETE  FROM video")
    fun deleteAll()
}