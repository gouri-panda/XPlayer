package com.one4ll.xplayer.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.one4ll.xplayer.models.Image

@Dao
interface ImageDao {
    @Insert
    fun insertImage(image: Image)

    @Delete
    fun deleteImage(image: Image)

    @Insert
    fun insertImages(image: List<Image>)

    @Query("SELECT * FROM image")
    fun getAll(): List<Image>

    @Query("DELETE  FROM image")
    fun deleteAll()
}