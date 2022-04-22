package com.one4ll.xplayer.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.one4ll.xplayer.models.Music
import com.one4ll.xplayer.models.Video

@Dao
interface MusicDao {
    @Insert
    fun insertMusic(image: Music)

    @Delete
    fun deleteMusic(image: Music)

    @Insert
    fun insertMusics(image: List<Music>)

    // todo add this later
//    @Query("SELECT * FROM music")
//    fun getAll(): List<Video>

    @Query("DELETE  FROM music")
    fun deleteAll()
}