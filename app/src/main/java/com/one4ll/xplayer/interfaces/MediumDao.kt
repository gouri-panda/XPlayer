package com.one4ll.xplayer.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.one4ll.xplayer.models.Medium

@Dao
interface MediumDao {
    @Insert
    fun insert(media: Medium)
    @Insert
    fun insertAll(medias : List<Medium>)
    @Query("SELECT * FROM media")
    fun getAll() : List<Medium>
    @Query("DELETE  FROM media")
    fun deleteAll()
}