package com.one4ll.xplayer.interfaces

import androidx.room.*
import com.one4ll.xplayer.models.Streams

@Dao
interface StreamsDao {
    @Insert
    fun insert(stream : Streams)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(stream: Streams)
    @Query("SELECT * FROM streams ORDER BY time DESC")
    fun getAllByTime() : List<Streams>
    @Query("DELETE  FROM streams")
    fun removeAll()
    @Query("SELECT * FROM streams WHERE time = :time")
    fun getValueBYId(time : Long) : List<Streams>
    @Query("UPDATE streams SET time = :time WHERE id = :id")
    fun updateTime(id : Long,time: Long)
}